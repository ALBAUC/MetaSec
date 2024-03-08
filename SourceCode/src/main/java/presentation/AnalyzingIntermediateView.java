package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.ANALYSIS_TYPE;
import presentation.frames.AnalyzingIntermediateFrame;

public class AnalyzingIntermediateView {

	private AnalyzingIntermediateFrame frame;

	public AnalyzingIntermediateView(PrincipalView principalView, ANALYSIS_TYPE type) {

		principalView.getFrame().setEnabled(false);
		AnalyzingView ac = new AnalyzingView(principalView, type);
		principalView.getFrame().setEnabled(true);
		frame = new AnalyzingIntermediateFrame();
		frame.setLocationRelativeTo(principalView.getFrame());
		frame.getBtnNewButton().setVisible(true);
		frame.getBtnNewButton_1().setVisible(true);
		

		if (ac.getNum() == 1) { // If there is only one file
			frame.getLblNewLabel().setText("We have processed 1 file");
			frame.setVisible(true);
		} else if (ac.getNum() != -1){ // If there are more than one file and there'snt an error.
			frame.getLblNewLabel().setText("We have processed " + ac.getNum() + " files");
			frame.setVisible(true);
		}
		frame.getBtnNewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ac.getFrame().setVisible(true);
				frame.dispose();

			}
		});

		frame.getBtnNewButton_1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveView(ac);
				frame.dispose();

			}
		});

	}

}
