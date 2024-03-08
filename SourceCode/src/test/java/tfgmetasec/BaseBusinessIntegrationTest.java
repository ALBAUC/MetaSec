package tfgmetasec;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import business.BaseBusiness;
import business.IBaseBusiness;
import domain.Analysis;
import domain.FILTER_TYPE;
import domain.FilterWithType;
import exceptions.ImpossibleToConnectException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaseBusinessIntegrationTest {

	private IBaseBusiness bb = new BaseBusiness();

	@Test
	@Order(value = 0)
	public void testAnalyzeURL() {

		// Pre-configuration
		File directory = new File(BaseBusinessIntegrationTest.class.getResource("/business/folder1").getFile());
		ProcessBuilder builder = new ProcessBuilder("python", "-m", "http.server", "8000");
		builder.directory(directory);
		Process process = null;
		int n = 0;
		try {
			process = builder.start();
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			process.destroy();
			assertTrue(false);
		}

		// IBB.3a Directory with 2 files and 1 directory with 1 file.
		Analysis a = new Analysis("http://localhost:8000/");
		try {
			n = bb.analyzeUrl(a);
		} catch (URISyntaxException | IOException | InterruptedException | ImpossibleToConnectException e) {
			process.destroy();
			System.out.println(e);
			assertTrue(false);
		}
		assertEquals(5, n); // 5 for 3 files because we have to count the index.html file for each directory
		assertEquals(94, a.getMetadataList().size());

		// IBB.3b 1 File
		a.setAbsolutePath("http://localhost:8000/folder1_1/3.pdf");
		try {
			n = bb.analyzeUrl(a);
		} catch (URISyntaxException | IOException | InterruptedException | ImpossibleToConnectException e) {
			process.destroy();
			System.out.println(e);
			assertTrue(false);
		}
		assertEquals(1, n);
		assertEquals(24, a.getMetadataList().size());

		// IBB.3c Wrong resource
		a.setAbsolutePath("http://localhost:8000/folder1fdsfsdf_1/3.pdf");
		assertThrows(Exception.class, () -> bb.analyzeUrl(a));

		// IBB.3d Wrong uri format
		a.setAbsolutePath("httpt://8000:localhost/folder1fdsfsdf_1/3.pdf");
		assertThrows(Exception.class, () -> bb.analyzeUrl(a));

		// Post-configuration
		process.destroy();
	}

	@Test
	@Order(value = 1)
	public void testAnalyzeAnalysis() {

		// Pre-configuration.
		File directory = new File(BaseBusinessIntegrationTest.class.getResource("/business/folder1").getFile());
		File f1 = new File(BaseBusinessIntegrationTest.class.getResource("/business/folder1/1.pdf").getFile());
		File emptyDirectory = new File(directory.getAbsolutePath() + File.separator + "folder1_2");
		if (!emptyDirectory.exists()) {
			emptyDirectory.mkdir();
		}
		int n;
		Analysis a;

		// IBB.4a Directory with 2 files and 1 directory with 1 file.
		a = new Analysis(directory.getAbsolutePath());
		n = bb.analyzeAnalysis(a, true);
		assertEquals(3, n);
		assertEquals(72, a.getMetadataList().size());

		// IBB.4b File.
		a = new Analysis(f1.getAbsolutePath());
		n = bb.analyzeAnalysis(a, true);
		assertEquals(1, n);
		assertEquals(24, a.getMetadataList().size());

		// IBB.4c Empty directory.
		a = new Analysis(emptyDirectory.getAbsolutePath());
		n = bb.analyzeAnalysis(a, true);
		assertEquals(0, n);
		assertEquals(0, a.getMetadataList().size());

		// Post-configuration.
		emptyDirectory.delete();
	}

	@Test
	@Order(value = 2)
	public void testDeleteAnalysis() {

		// Pre-configuration.
		Analysis a;
		int n;
		File businessFolder = new File(BaseBusinessIntegrationTest.class.getResource("/business").getFile());
		File fsrc1 = new File(businessFolder.getAbsolutePath() + File.separator + "folder2" + File.separator + "1.pdf");
		File fdst1 = new File(businessFolder.getAbsolutePath() + File.separator + "folder3" + File.separator + "1.pdf");
		try {
			Files.copy(Paths.get(fsrc1.getAbsolutePath()), Paths.get(fdst1.getAbsolutePath()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			assertTrue(false);
		}

		// IBB.5a File with metadata.
		a = new Analysis(fdst1.getAbsolutePath());
		n = bb.deleteAnalysis(a);
		assertEquals(1, n);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			assertTrue(false);
		}
		bb.analyzeAnalysis(a, true);
		assertEquals(15, a.getMetadataList().size());

		// IBB.5b File with metadata deleted.
		n = bb.deleteAnalysis(a);
		assertEquals(1, n);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			assertTrue(false);
		}
		bb.analyzeAnalysis(a, true);
		assertEquals(15, a.getMetadataList().size());

		// IBB.5c Empty directory.
		File emptyDirectory = new File(businessFolder.getAbsolutePath() + File.separator + "folder4");
		emptyDirectory.mkdir();
		a = new Analysis(emptyDirectory.getAbsolutePath());
		n = bb.deleteAnalysis(a);
		assertEquals(0, n);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			assertTrue(false);
		}
		bb.analyzeAnalysis(a, true);
		assertEquals(0, a.getMetadataList().size());

		// IBB.5d Directory with 2 files and 1 directory with 1 file.
		File fsrc2 = new File(businessFolder.getAbsolutePath() + File.separator + "folder2" + File.separator + "1.pdf");
		File fdst2 = new File(businessFolder.getAbsolutePath() + File.separator + "folder4" + File.separator + "1.pdf");
		File fsrc3 = new File(businessFolder.getAbsolutePath() + File.separator + "folder2" + File.separator + "2.pdf");
		File fdst3 = new File(businessFolder.getAbsolutePath() + File.separator + "folder4" + File.separator + "2.pdf");
		File folder4_1 = new File(
				businessFolder.getAbsolutePath() + File.separator + "folder4" + File.separator + "folder4_1");
		folder4_1.mkdir();
		File fsrc4 = new File(businessFolder.getAbsolutePath() + File.separator + "folder2" + File.separator + "3.pdf");
		File fdst4 = new File(businessFolder.getAbsolutePath() + File.separator + "folder4" + File.separator
				+ "folder4_1" + File.separator + "3.pdf");
		try {
			Files.copy(Paths.get(fsrc2.getAbsolutePath()), Paths.get(fdst2.getAbsolutePath()),
					StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(fsrc3.getAbsolutePath()), Paths.get(fdst3.getAbsolutePath()),
					StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(fsrc4.getAbsolutePath()), Paths.get(fdst4.getAbsolutePath()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			assertTrue(false);
		}

		n = bb.deleteAnalysis(a);
		assertEquals(3, n);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			assertTrue(false);
		}
		bb.analyzeAnalysis(a, true);
		assertEquals(45, a.getMetadataList().size());

		// Post-configuration.
		fdst4.delete();
		folder4_1.delete();
		fdst3.delete();
		fdst2.delete();
		emptyDirectory.delete();

	}

	@Test
	@Order(value = 3)
	public void testSaveAnalysis() {
		// Pre-configuration.
		String home = System.getProperty("user.home");
		String appPath = home + File.separator + "METASEC";
		String projectsPath = appPath + File.separator + "projects";
		File projectsDirectory = new File(projectsPath);
		File appDirectory = new File(appPath);
		File appDirectoryAux = null;
		if (appDirectory.exists()) {
			appDirectoryAux = new File(appDirectory.getParent(), "METASEC_TEMP");
			appDirectory.renameTo(appDirectoryAux);
		}
		Analysis a1 = new Analysis("a1");
		a1.setName("a1");
		a1.setMetadataList(null);
		Analysis a2 = new Analysis("a2");
		a2.setName("a2");
		a2.setMetadataList(null);

		// IBB.2a METASEC directory and file don't exist.
		assertDoesNotThrow(() -> {
			assertTrue(bb.saveAnalysis(a1));
			Analysis a = bb.getAnalysis(projectsPath + File.separator + a1.getName() + ".json");
			assertEquals(a1.getName(), a.getName());
			assertEquals(a1.getAbsolutePath(), a.getAbsolutePath());
			assertNull(a.getMetadataList());
		});
		// IBB.2b METASEC directory and file exist.
		assertDoesNotThrow(() -> {
			assertFalse(bb.saveAnalysis(a1));
		});
		// IBB.2c METASEC directory exists and file doesn't.
		assertDoesNotThrow(() -> {
			assertTrue(bb.saveAnalysis(a2));
			Analysis a = bb.getAnalysis(projectsPath + File.separator + a2.getName() + ".json");
			assertEquals(a2.getName(), a.getName());
			assertEquals(a2.getAbsolutePath(), a.getAbsolutePath());
			assertNull(a.getMetadataList());
		});
		// Post-configuration.
		File f1 = new File(projectsPath + File.separator + a1.getName() + ".json");
		File f2 = new File(projectsPath + File.separator + a2.getName() + ".json");
		f1.delete();
		f2.delete();
		projectsDirectory.delete();
		appDirectory.delete();

		if (appDirectoryAux != null) {
			appDirectoryAux.renameTo(new File(appDirectory.getParent(), "METASEC"));
			appDirectoryAux.delete();
		}

	}

	@Test
	@Order(value = 4)
	public void testGetAnalysis() {
		File correctJson = new File(BaseBusinessIntegrationTest.class.getResource("/dao/correct.json").getFile());
		File incorrectJson = new File(BaseBusinessIntegrationTest.class.getResource("/dao/incorrect.json").getFile());
		File emptyJson = new File(BaseBusinessIntegrationTest.class.getResource("/dao/empty.json").getFile());
		File correctFile = new File(BaseBusinessIntegrationTest.class.getResource("/dao/incorrect.txt").getFile());

		// IBB.1a Correct
		assertDoesNotThrow(() -> {
			Analysis a = bb.getAnalysis(correctJson.getAbsolutePath());
			assertEquals("prueba", a.getName());
			assertEquals("C:\\Users\\gimen\\Documents\\CV", a.getAbsolutePath());
			assertEquals(69, a.getMetadataList().size());
		});
		// IBB.1b Incorrect Json format
		assertThrows(IOException.class, () -> bb.getAnalysis(incorrectJson.getAbsolutePath()));
		// IBB.1c Empty json
		assertThrows(IOException.class, () -> bb.getAnalysis(emptyJson.getAbsolutePath()));
		// IBB.1d Not a json
		assertThrows(IOException.class, () -> bb.getAnalysis(correctFile.getAbsolutePath()));
		// IBB.1e File doesn´t exist
		assertDoesNotThrow(() -> {
			assertNull(bb.getAnalysis("/akldfj.txt"));
		});
	}

	@Test
	@Order(value = 5)
	public void testFilter() {

		JTable table;
		FilterWithType ft;
		RowFilter<Object, Object> newFilter;
		FilterWithType result;

		// IBB.6a TEXT IS NULL ---------------------------------

		// Pre-configuration.
		List<FilterWithType> filtersList = new LinkedList<FilterWithType>();
		String[] cols = { "Key", "Value", "Path" };
		String[][] matrix = new String[3][3];
		String textBase = "fwt";
		String text = "";
		for (int i = 0; i < 3; i++) {
			text = textBase + i;
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			matrix[i][2] = text + "2";

		}
		table = new JTable(matrix, cols);

		// Test
		assertNull(bb.filter(null, null, filtersList, table));
		assertEquals(3, table.getRowCount());

		// IBB.6b TEXT IS EMPTY -------------------------------------------
		assertNull(bb.filter("", null, filtersList, table));
		assertEquals(3, table.getRowCount());

		// IBB.6c 0 FILTERS + KEY FILTER ---------------------------------

		// Pre-configuration
		newFilter = RowFilter.regexFilter("(?i)" + "fwt1", 0);
		ft = new FilterWithType(newFilter, "fwt1", FILTER_TYPE.KEY);

		// Test
		result = bb.filter("fwt1", FILTER_TYPE.KEY, filtersList, table);
		assertEquals(1, filtersList.size());
		assertEquals(result, filtersList.get(0));
		assertEquals(1, table.getRowSorter().getViewRowCount());
		assertEquals(ft, result);

		// IBB.6d 1 FILTER + VALUE FILTER ----------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			if (i == 1) {
				matrix[i][1] = text + "one";
			} else {
				matrix[i][1] = text + "1";
			}
			matrix[i][2] = text + "2";

		}
		table = new JTable(matrix, cols);
		filtersList.clear();
		newFilter = RowFilter.regexFilter("(?i)" + "one", 1);
		ft = new FilterWithType(newFilter, "one", FILTER_TYPE.VALUE);
		bb.filter("fwt1", FILTER_TYPE.KEY, filtersList, table);

		// Test
		result = bb.filter("one", FILTER_TYPE.VALUE, filtersList, table);
		assertEquals(2, filtersList.size());
		assertEquals(result, filtersList.get(1));
		assertEquals(1, table.getRowSorter().getViewRowCount());
		assertEquals(ft, result);

		// IBB.6e 1 FILTER + PATH FILTER --------------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			if (i == 1) {
				matrix[i][2] = text + "one";
			} else {
				matrix[i][2] = text + "2";
			}

		}
		table = new JTable(matrix, cols);
		filtersList.clear();
		newFilter = RowFilter.regexFilter("(?i)" + "one", 2);
		ft = new FilterWithType(newFilter, "one", FILTER_TYPE.PATH);
		bb.filter("fwt1", FILTER_TYPE.KEY, filtersList, table);

		// Test
		result = bb.filter("one", FILTER_TYPE.PATH, filtersList, table);
		assertEquals(2, filtersList.size());
		assertEquals(result, filtersList.get(1));
		assertEquals(1, table.getRowSorter().getViewRowCount());
		assertEquals(ft, result);

		// IBB.6f 0 FILTER + NAME FILTER ---------------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			matrix[i][2] = text + "2";
			if (i == 1) {
				matrix[i][0] = "creator";
			} else if (i == 2) {
				matrix[i][0] = "author";
			}
		}
		table = new JTable(matrix, cols);
		filtersList.clear();

		// Test
		result = bb.filter("NAME", FILTER_TYPE.NAME, filtersList, table);
		assertEquals(1, filtersList.size());
		assertEquals(result, filtersList.get(0));
		assertEquals(2, table.getRowSorter().getViewRowCount());

		// IBB.6g 0 FILTER + EMAIL FILTER ---------------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			matrix[i][2] = text + "2";
			if (i == 1) {
				matrix[i][1] = "example@gmail.com";
			} else if (i == 2) {
				matrix[i][1] = "example2@gmail.com";
			}

		}
		table = new JTable(matrix, cols);
		filtersList.clear();

		// Test
		result = bb.filter("EMAIL", FILTER_TYPE.EMAIL, filtersList, table);
		assertEquals(1, filtersList.size());
		assertEquals(result, filtersList.get(0));
		assertEquals(2, table.getRowSorter().getViewRowCount());

		// IBB.6h 2 FILTERS + GPS FILTER ------------------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			matrix[i][2] = text + "2";
			if (i == 1) {
				matrix[i][0] = "gps";
			} else if (i == 2) {
				matrix[i][0] = "latitude";
			}

		}
		table = new JTable(matrix, cols);
		filtersList.clear();
		bb.filter("fwt11", FILTER_TYPE.VALUE, filtersList, table);
		bb.filter("fwt12", FILTER_TYPE.PATH, filtersList, table);

		// IBB.6i 0 FILTERS + GPS FILTER WITH 0 RESULTS ------------------------------------------

		// Pre-configuration
		matrix = new String[4][3];
		text = "fwt1";
		for (int i = 0; i < 4; i++) {
			matrix[i][0] = text + "0";
			matrix[i][1] = text + "1";
			matrix[i][2] = text + "2";
		}
		table = new JTable(matrix, cols);
		filtersList.clear();

		// Test
		result = bb.filter("GPS", FILTER_TYPE.GPS, filtersList, table);
		assertEquals(1, filtersList.size());
		assertEquals(result, filtersList.get(0));
		assertEquals(0, table.getRowSorter().getViewRowCount());

	}

}
