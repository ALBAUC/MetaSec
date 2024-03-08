package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

import domain.ANALYSIS_TYPE;
import presentation.frames.ErrorFrame;
import presentation.frames.PrincipalFrame;


public class PrincipalView {

	private JFileChooser chooser;
	private PrincipalView view;
	private PrincipalFrame frame;

	
	public PrincipalView() {
		
		frame = new PrincipalFrame();
		view = this;
		// Set visible the principal frame
		frame.setVisible(true);

		// Open the JFileChooser to select the path of the file or directory.
		frame.getBtnNewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int answer = chooser.showOpenDialog(null);
				if (answer == JFileChooser.APPROVE_OPTION) {
					frame.getTextField().setText(chooser.getSelectedFile().getAbsolutePath());
				}

			}
		});
		
		// Analyze metadata in internet
		frame.getBtnNewButton_4().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getTextField().getText().equals("")) {
					return;
				}
				new AnalyzingIntermediateView(view,ANALYSIS_TYPE.INTERNET);
				
			}
		});
		
		// Analyze metadata in local
		frame.getBtnNewButton_1().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(frame.getTextField().getText().equals("")) {
					return;
				}
				File file = new File(frame.getTextField().getText());
				if(!file.exists()) {
					ErrorFrame ef = new ErrorFrame("ERROR!! There is no file or directory with that path");
					ef.setLocationRelativeTo(frame);
					return;
				}
				new AnalyzingIntermediateView(view,ANALYSIS_TYPE.LOCAL);
				
			}
		});
		
		// Delete metadata
		frame.getBtnNewButton_2().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getTextField().getText().equals("")) {
					return;
				}
				File file = new File(frame.getTextField().getText());
				if(!file.exists()) {
					ErrorFrame ef = new ErrorFrame("ERROR!! There is no file or directory with that path");
					ef.setLocationRelativeTo(frame);
					return;
				}
				new DeletedView(view, getPath());
				
			}
		});
		
		// Open project
		frame.getBtnNewButton_3().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				File home = new File(System.getProperty("user.home"));
				String projectsDirecotry = home.getAbsolutePath() + File.separator + "METASEC" + File.separator + "projects";
				chooser.setCurrentDirectory(new File(projectsDirecotry));
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int answer = chooser.showOpenDialog(null);
				if (answer == JFileChooser.APPROVE_OPTION) {
					frame.getTextField().setText(chooser.getSelectedFile().getAbsolutePath());
					new AnalyzingView(view, ANALYSIS_TYPE.PROJECT);
				}
				
				
				
			}
		});
	}
	
	public String getPath() {
		return frame.getTextField().getText();
	}
	
	
	public PrincipalFrame getFrame() {
		return frame;
	}
	

}
