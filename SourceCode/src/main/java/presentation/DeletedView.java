package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.BaseBusiness;
import business.IBaseBusiness;
import domain.ANALYSIS_TYPE;
import domain.Analysis;
import presentation.frames.DeletedFrame;

public class DeletedView {
	
	private IBaseBusiness business;
	private DeletedFrame frame;

	public DeletedView(PrincipalView principalView, String path) {
		business = new BaseBusiness();
		principalView.getFrame().setEnabled(false);
		int num = business.deleteAnalysis(new Analysis(path));
		principalView.getFrame().setEnabled(true);
		frame = new DeletedFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(principalView.getFrame());
		frame.getLblNewLabel_1().setText("Number of files processed: " + num);
		frame.getLblNewLabel().setText("SENSIBLE METADATA DELETED");
		frame.getBtnNewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnalyzingView ac = new AnalyzingView(principalView, ANALYSIS_TYPE.LOCAL);
				frame.dispose();
				ac.getFrame().setVisible(true);

			}
		});

	}

}
