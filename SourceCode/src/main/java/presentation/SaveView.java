package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import business.BaseBusiness;
import business.IBaseBusiness;
import presentation.frames.ErrorFrame;
import presentation.frames.SaveFrame;


public class SaveView {

	private SaveFrame frame;
	private IBaseBusiness business;

	public SaveView(AnalyzingView analyzingView) {
		business = new BaseBusiness();
		frame = new SaveFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(analyzingView.getFrame());
		frame.getBtnNewButton().addActionListener(new ActionListener() {

			// Save the project.
			@Override
			public void actionPerformed(ActionEvent e) {

				analyzingView.getAnalysis().setName(frame.getTextField().getText());
				boolean result;
				try {
					result = business.saveAnalysis(analyzingView.getAnalysis());
					if (!result) {
						ErrorFrame ef = new ErrorFrame("ERROR!! There is one existing project with that name");
						ef.setLocationRelativeTo(frame);
					}
					frame.dispose();
				} catch (IOException e1) {
					ErrorFrame ef = new ErrorFrame("ERROR!! Error processing the file");
					ef.setLocationRelativeTo(frame);
				}

			}
		});
	}

	public SaveFrame getFrame() {
		return frame;
	}
}
