package tfgmetasec;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;



import dao.AnalysisDAO;
import dao.IAnalysisDAO;
import domain.Analysis;

class AnalysisDAOIntegrationTest {

	private IAnalysisDAO dao = new AnalysisDAO();

	@Test
	public void testGetAnalysis() {

		// Pre-configuration
		File correctJson = new File(AnalysisDAOIntegrationTest.class.getResource("/dao/correct.json").getFile());
		File incorrectJson = new File(AnalysisDAOIntegrationTest.class.getResource("/dao/incorrect.json").getFile());
		File emptyJson = new File(AnalysisDAOIntegrationTest.class.getResource("/dao/empty.json").getFile());
		File correctFile = new File(AnalysisDAOIntegrationTest.class.getResource("/dao/incorrect.txt").getFile());

		// IAD.1a Correct
		assertDoesNotThrow(() -> {
			Analysis a = dao.getAnalysis(correctJson.getAbsolutePath());
			assertEquals("prueba", a.getName());
			assertEquals("C:\\Users\\gimen\\Documents\\CV", a.getAbsolutePath());
			assertEquals(69, a.getMetadataList().size());
		});
		
		// IAD.1b Incorrect Json format
		assertThrows(IOException .class, () -> dao.getAnalysis(incorrectJson.getAbsolutePath()));
		
		// IAD.1c Empty json
		assertThrows(IOException .class, () -> dao.getAnalysis(emptyJson.getAbsolutePath()));
		
		
		// IAD.1d Not a json
		assertThrows(IOException.class, () -> dao.getAnalysis(correctFile.getAbsolutePath()));
		

		// IAD.1e File doesn´t exist
		assertDoesNotThrow(() -> {
			assertNull(dao.getAnalysis("/akldfj.txt"));
		});
		

	}

	
	@Test
	public void testSaveAnalysis() {
		
		// Pre-configuration.
		String home = System.getProperty("user.home");
		String appPath = home + File.separator + "METASEC";
		String projectsPath = appPath + File.separator + "projects";
		File projectsDirectory = new File(projectsPath);
		File appDirectory = new File(appPath);
		File appDirectoryAux = null;
		if(appDirectory.exists()) {
			appDirectoryAux = new File(appDirectory.getParent(), "METASEC_TEMP");
			appDirectory.renameTo(appDirectoryAux);
			
		}
		Analysis a1 = new Analysis("a1");
		a1.setName("a1");
		a1.setMetadataList(null);
		Analysis a2 = new Analysis("a2");
		a2.setName("a2");
		a2.setMetadataList(null);
		
		
		// IAD.2a METASEC directory and file don't exist.
		assertDoesNotThrow(() -> {
			assertTrue(dao.saveAnalysis(a1));
			Analysis a = dao.getAnalysis(projectsPath + File.separator + a1.getName() + ".json");
			assertEquals(a1.getName(), a.getName());
			assertEquals(a1.getAbsolutePath(), a.getAbsolutePath());
			assertNull(a.getMetadataList());
		});
		
		// IAD.2b METASEC directory and file exist.
		assertDoesNotThrow(() -> {
		assertFalse(dao.saveAnalysis(a1));
		});
		
		// IAD.2c METASEC directory exists and file doesn't.
		assertDoesNotThrow(() -> {
			assertTrue(dao.saveAnalysis(a2));
			Analysis a = dao.getAnalysis(projectsPath + File.separator + a2.getName() + ".json");
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
			System.out.println("p control");
			appDirectoryAux.renameTo(new File(appDirectory.getParent(), "METASEC"));
			appDirectoryAux.delete();
		}
	

	}
	
}
