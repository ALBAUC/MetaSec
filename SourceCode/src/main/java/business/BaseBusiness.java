package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import constants.Constants;
import dao.AnalysisDAO;
import dao.IAnalysisDAO;
import domain.Analysis;
import domain.FILTER_TYPE;
import domain.FilterWithType;
import domain.Metadata;
import exceptions.ImpossibleToConnectException;

public class BaseBusiness implements IBaseBusiness {

	private int fileCounter;
	private IAnalysisDAO dao;

	public BaseBusiness() {
		dao = new AnalysisDAO();
		fileCounter = 0;

	}

	private synchronized void incrementFileCounter() {
		fileCounter++;
	}

	private synchronized int getFileCounter() {
		return fileCounter;
	}

	/**
	 * Deletes the exif metadata of the file or files from the directory which has
	 * the same path as the value of the variable absolutePath of the argument a.
	 * 
	 * @param a The analysis that contains the metadata that we want to delete.
	 * 
	 * @return The number of files that have been analyzed.
	 */
	public int deleteAnalysis(Analysis a) {
		fileCounter = 0;
		deleteAnalysis(a.getAbsolutePath());
		int num = getFileCounter();
		return num;
	}

	/**
	 * Creates the structure of the analysis by creating and adding all the Metadata
	 * objects to its list of metadata.
	 * 
	 * @param a The analysis that has the path that we want to analyze.
	 * @param local Indicates weather it is a local analysis or an online analysis.
	 * 
	 * @return The number of files that have been analyzed.
	 */
	public int analyzeAnalysis(Analysis a, boolean local) {
		if(!a.getMetadataList().isEmpty()) {
			a.setMetadataList(Collections.synchronizedList(new LinkedList<Metadata>()));
		}
		fileCounter = 0;
		analyzeAnalysis(a.getAbsolutePath(), a, local);
		int num = getFileCounter();
		return num;
	}

	/**
	 * Gets an analysis that is in a json format.
	 * 
	 * @param path The path of the json.
	 * @throws IOException If there is any problem in the file mapping.
	 * @return The analysis. Null if the analysis doesn't exist.
	 * 
	 */
	public Analysis getAnalysis(String path) throws IOException{
		return dao.getAnalysis(path);
	}

	/**
	 * Saves an analysis.
	 * 
	 * @param analysis The analysis to be saved.
	 * @throws IOException If there was any problem with the file mapping process.
	 * @return True if everything was ok, False if there is a project with that name.
	 */
	public boolean saveAnalysis(Analysis analysis) throws IOException {
		return dao.saveAnalysis(analysis);

	}

	/**
	 * Downloads an analyzes a URL then, deletes what has been downloaded.
	 * @param analysis The analysis with the url.
	 * @return the amount of files that have been processed.
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ImpossibleToConnectException 
	 */
	public int analyzeUrl(Analysis analysis)
			throws URISyntaxException, IOException, InterruptedException, ImpossibleToConnectException {

		// Check connection is possible
		URL url = new URL(analysis.getAbsolutePath());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(1000);
		connection.setRequestMethod("HEAD");
		int responseCode = connection.getResponseCode();

		// Create directory for downloads if it doesn't exist
		String home = System.getProperty("user.home");
		String appPath = home + File.separator + "METASEC";
		String downloadsPath = appPath + File.separator + "tempDownloads";
		File downloadsDirectory = new File(downloadsPath);
		File appDirectory = new File(appPath);
		if (!downloadsDirectory.exists()) {
			if (!appDirectory.exists()) {
				appDirectory.mkdir();
			}
			downloadsDirectory.mkdir();
		}

		// Download data
		if (200 <= responseCode && responseCode < 300) {
			URI uri = new URI(analysis.getAbsolutePath());
			List<String> command = new ArrayList<>();
			command.add("wget.exe");
			command.add("--mirror");
			command.add("--convert-links");
			command.add("--no-parent");
			command.add("--domains=" + uri.getHost());
			command.add("--reject");
			command.add("index.html");
			command.add("-P");
			command.add(downloadsDirectory.getAbsolutePath() + File.separator + uri.getHost());
			command.add(uri.toString());

			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.inheritIO();
			Process paux = processBuilder.start();
			paux.waitFor();

			// Analyze metadata
			analysis.setAbsolutePath(downloadsPath + File.separator + uri.getHost());
			int n =  analyzeAnalysis(analysis, false);
			File f = new File(downloadsDirectory.getAbsolutePath() + File.separator + uri.getHost());
			removeEmptyDirectories(f);
			f = new File(analysis.getAbsolutePath());
			f.delete();
			return n;
		} else {
			throw new ImpossibleToConnectException();
		}

	}
	
	/**
	 * Removes all the directories of a directory.
	 * @param file Directory to be removed.
	 */
	private void removeEmptyDirectories(File file) {
		for(File f: file.listFiles()) {
			removeEmptyDirectories(f);
			f.delete();
		}
	}

	/**
	 * Creates the structure of the analysis by creating and adding the Metadata
	 * objects to a´s metadata list.
	 * 
	 * @param path The path of the directory or file that we want to analyze.
	 * @param a    The analysis.
	 */
	private void analyzeAnalysis(String path, Analysis a, boolean local) {

		// Get the file from the path
		File file = new File(path);

		// Analyze directories
		if (file.isDirectory()) {
			// Parallel processing
			File[] files = file.listFiles();
			List<Thread> threads = new ArrayList<Thread>();

			for (File f : files) {
				Thread thread = new Thread(() -> {
					analyzeAnalysis(f.getAbsolutePath(), a, local);
				});
				thread.start();
				threads.add(thread);

			}
			
			threads.forEach(thread -> {
				try {
					thread.join();
				} catch (InterruptedException e) {
					return;
				}
			});

		}

		// Analyze files and add the metadata to the list
		if (file.isFile()) {
			try {

				ProcessBuilder processBuilder = new ProcessBuilder("exiftool", path);
				Process process = processBuilder.start();
				incrementFileCounter();
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				boolean primero = true;
				while ((line = reader.readLine()) != null) {
					if (primero) {
						primero = false;
						continue;
					}
					String[] partes = line.split(":", 2);
					if (partes.length == 2) {
						a.getMetadataList().add(new Metadata(partes[0].trim(), partes[1].trim(), path));
					}
				}
				if(!local) {
					file.delete();
				}

			} catch (IOException e) {
				return;
			}
		}

	}

	/**
	 * Deletes the exif metadata of the file or files from the path that is given.
	 * The path can be a directory or a file. If it's a directory, it will run all
	 * over its files or directories, deleting the metadata.
	 * 
	 * @param path The path of the file or directory.
	 */
	private void deleteAnalysis(String path) {

		// Get the file from the path
		File file = new File(path);

		// Move through directories
		if (file.isDirectory()) {
			// Parallel processing
			File[] files = file.listFiles();
			List<Thread> threads = new ArrayList<Thread>();
			for (File f : files) {
				Thread thread = new Thread(() -> {
					deleteAnalysis(f.getAbsolutePath());
				});
				thread.start();
				threads.add(thread);
			}
			threads.forEach(thread -> {
				try {
					thread.join();
				} catch (InterruptedException e) {

				}
			});

		}

		// Delete the metadata of this file
		if (file.isFile()) {
			try {
				ProcessBuilder processBuilder = new ProcessBuilder("exiftool", "-all=", "-overwrite_original", path);
				processBuilder.start();
				incrementFileCounter();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Adds a filter. If text is null or empty, it filters with the filters written
	 * in the filtersList.
	 * 
	 * @param text        Text that is going to act as the filter.
	 * @param type        Type of filter.
	 * @param filtersList List with the filters and it's type.
	 * @param table       Table to be filtered.
	 * @return The FilterWithType object. If text is null or empty, returns null.
	 */
	public FilterWithType filter(String text, FILTER_TYPE type, List<FilterWithType> filtersList, JTable table) {

		// Create the final result (It is only filled with the existing ones, not
		// finished)
		RowFilter<Object, Object> resultFilter = fillWithExistingFilters(filtersList);
		RowFilter<Object, Object> newFilter = null;
		List<RowFilter<Object, Object>> list = new ArrayList<RowFilter<Object, Object>>();
		list.add(resultFilter);
		FilterWithType ft = null;
		RowFilter<Object, Object> sub1;
		List<RowFilter<Object, Object>> preSub2;
		if (text != null) {
			if (!text.isEmpty()) {
				switch (type) {
				case KEY:
					newFilter = RowFilter.regexFilter("(?i)" + text, 0);
					ft = new FilterWithType(newFilter, text, FILTER_TYPE.KEY);
					filtersList.add(ft);
					list.add(newFilter);
					resultFilter = RowFilter.andFilter(list);
					break;
				case VALUE:
					newFilter = RowFilter.regexFilter("(?i)" + text, 1);
					ft = new FilterWithType(newFilter, text, FILTER_TYPE.VALUE);
					filtersList.add(ft);
					list.add(newFilter);
					resultFilter = RowFilter.andFilter(list);
					break;
				case PATH:
					newFilter = RowFilter.regexFilter("(?i)" + text, 2);
					ft = new FilterWithType(newFilter, text, FILTER_TYPE.PATH);
					filtersList.add(ft);
					list.add(newFilter);
					resultFilter = RowFilter.andFilter(list);
					break;
				case NAME:
					sub1 = resultFilter;
					preSub2 = new ArrayList<RowFilter<Object, Object>>();
					for (String txt : Constants.NAME_FILTER_LIST) {
						preSub2.add(RowFilter.regexFilter("(?i)" + txt, 0));
					}
					newFilter = RowFilter.orFilter(preSub2);
					ft = new FilterWithType(newFilter, "NAME", FILTER_TYPE.NAME);
					filtersList.add(ft);

					// Create the final filter
					resultFilter = RowFilter.andFilter(Arrays.asList(sub1, newFilter));
					break;
				case EMAIL:
					newFilter = RowFilter.regexFilter("(?i)" + "@", 1);
					ft = new FilterWithType(newFilter, "EMAIL", FILTER_TYPE.EMAIL);
					filtersList.add(ft);
					list.add(newFilter);
					resultFilter = RowFilter.andFilter(list);
					break;
				case GPS:
					sub1 = resultFilter;
					preSub2 = new ArrayList<RowFilter<Object, Object>>();
					for (String txt : Constants.GPS_FILTER_LIST) {
						preSub2.add(RowFilter.regexFilter("(?i)" + txt, 0));
					}
					newFilter = RowFilter.orFilter(preSub2);
					ft = new FilterWithType(newFilter, "GPS", FILTER_TYPE.GPS);
					filtersList.add(ft);
					// Create the final filter
					resultFilter = RowFilter.andFilter(Arrays.asList(sub1, newFilter));
				}
			}
		}

		// Apply the new filter mixed with the existing ones.
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
		rowSorter.setRowFilter(resultFilter);
		table.setRowSorter(rowSorter);

		return ft;

	}

	/**
	 * Creates a unique filter that is composed of all the existing filters in
	 * filtersList using the AND operator.
	 * 
	 * @return The unique filter
	 */
	private RowFilter<Object, Object> fillWithExistingFilters(List<FilterWithType> filtersList) {
		List<RowFilter<Object, Object>> existingFilters = new ArrayList<RowFilter<Object, Object>>();
		for (FilterWithType f : filtersList) {
			existingFilters.add(f.getFilter());

		}
		return RowFilter.andFilter(existingFilters);
	}

}
