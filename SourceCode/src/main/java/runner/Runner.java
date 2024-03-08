package runner;

import java.awt.EventQueue;

import presentation.PrincipalView;
import presentation.frames.ErrorFrame;

public class Runner {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PrincipalView();
				} catch (Exception e) {
					new ErrorFrame("Metasec couldn't be launched");
				}
			}
		});
	}

}
