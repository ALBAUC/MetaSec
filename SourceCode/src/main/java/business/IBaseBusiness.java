package business;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JTable;

import domain.Analysis;
import domain.FILTER_TYPE;
import domain.FilterWithType;
import exceptions.ImpossibleToConnectException;

public interface IBaseBusiness {
	
	/**
	 * Gets an analysis that is in a json format.
	 * 
	 * @param path The path of the json.
	 * @throws IOException If there is any problem in the file mapping.
	 * @return The analysis. Null if the analysis doesn't exist.
	 * 
	 */
	public Analysis getAnalysis(String path) throws IOException;
	
	/**
	 * Saves an analysis.
	 * 
	 * @param analysis The analysis to be saved.
	 * @throws IOException If there was any problem with the file mapping process.
	 * @return True if everything was ok, False if there is a project with that name.
	 */
	public boolean saveAnalysis(Analysis analysis) throws IOException;
	
	/**
	 * Deletes the exif metadata of the file or files from the directory which has
	 * the same path as the value of the variable absolutePath of the argument a.
	 * 
	 * @param a The analysis that contains the metadata that we want to delete.
	 * 
	 * @return The number of files that have been analyzed.
	 */
	public int deleteAnalysis(Analysis a);
	
	/**
	 * Creates the structure of the analysis by creating and adding all the Metadata
	 * objects to its list of metadata.
	 * 
	 * @param a The analysis that has the path that we want to analyze.
	 * @param local Indicates weather it is a local analysis or an online analysis.
	 * 
	 * @return The number of files that have been analyzed.
	 */
	public int analyzeAnalysis(Analysis a, boolean local);
	
	
	/**
	 * Downloads an analyzes a URL then, deletes what has been downloaded.
	 * @param analysis The analysis with the url.
	 * @return the amount of files that have been processed.
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ImpossibleToConnectException 
	 */
	public int analyzeUrl(Analysis analysis) throws URISyntaxException, IOException, InterruptedException, ImpossibleToConnectException;
	
	/**
	 * Adds a filter. If text is null or empty, it filters with the filters written
	 * in the filtersList.
	 * 
	 * @param text        Text that is going to act as the filter.
	 * @param type        Type of filter.
	 * @param filtersList List with the filters and it's type.
	 * @param table       Table to be filtered.
	 * @return The FilterWithType object.
	 */
	public FilterWithType filter(String text, FILTER_TYPE type, List<FilterWithType> filtersList, JTable table);

}
