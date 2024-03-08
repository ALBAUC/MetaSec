package dao;

import java.io.IOException;

import domain.Analysis;

public interface IAnalysisDAO {
	
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
	 * Saves an analysis in the projects folder created by the application (userPath\METASEC\projects).
	 * 
	 * @param analysis The analysis to be saved.
	 * @throws IOException If there was any problem with the file mapping process.
	 * @return True if everything was ok, False if there is a project with that name.
	 */
	public boolean saveAnalysis(Analysis analysis) throws IOException;
}

