package dao;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Analysis;

public class AnalysisDAO implements IAnalysisDAO {

	/**
	 * Gets an analysis that is in a json format.
	 * 
	 * @param path The path of the json.
	 * @throws IOException If there is any problem in the file mapping process.
	 * @return The analysis. Null if the analysis doesn't exist.
	 * 
	 */
	public Analysis getAnalysis(String path) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		Analysis a = mapper.readValue(f, Analysis.class);
		return a;

	}

	/**
	 * Saves an analysis.
	 * 
	 * @param analysis The analysis to be saved.
	 * @throws IOException If there was any problem with the file mapping process.
	 * @return True if everything was ok, False if there is a project with that
	 *         name.
	 */
	public boolean saveAnalysis(Analysis analysis) throws IOException {
		String home = System.getProperty("user.home");
		String appPath = home + File.separator + "METASEC";
		String projectsPath = appPath + File.separator + "projects";
		File projectsDirectory = new File(projectsPath);
		File appDirectory = new File(appPath);
		File project = new File(projectsPath + File.separator + analysis.getName() + ".json");
		if (!projectsDirectory.exists()) {
			if (!appDirectory.exists()) {
				appDirectory.mkdir();
			}
			projectsDirectory.mkdir();
		} else if (project.exists()) {
			return false;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(project, analysis);
		return true;

	}
}
