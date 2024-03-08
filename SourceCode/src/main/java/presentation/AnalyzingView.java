package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import business.BaseBusiness;
import business.IBaseBusiness;
import domain.ANALYSIS_TYPE;
import domain.Analysis;
import domain.FILTER_TYPE;
import domain.FilterWithType;
import domain.Metadata;
import exceptions.ImpossibleToConnectException;
import presentation.frames.AnalyzingFrame;
import presentation.frames.ErrorFrame;

public class AnalyzingView {

	private Analysis analysis;
	private JTable table;
	private String[][] matrix;
	private JScrollPane scroll;
	private List<FilterWithType> filtersList;
	private AnalyzingFrame frame;
	private int num;
	private String path;
	private AnalyzingView view;
	private IBaseBusiness business;

	public AnalyzingView(PrincipalView principalView, ANALYSIS_TYPE type) {

		business = new BaseBusiness();

		view = this;
		path = principalView.getPath();
		frame = new AnalyzingFrame();
		frame.setLocationRelativeTo(principalView.getFrame());

		// Create the list of filters
		filtersList = new ArrayList<FilterWithType>();

		// Create a new analysis or open an existing one
		switch (type) {

		case LOCAL:
			analysis = new Analysis(path);
			num = business.analyzeAnalysis(analysis,true);
			break;

		case PROJECT:
			frame.getBtnNewButton_7().setVisible(false);
			try {
				analysis = business.getAnalysis(path);
				if (analysis == null) {
					ErrorFrame ef = new ErrorFrame("ERROR!! There is no analysis with that name");
					ef.setLocationRelativeTo(principalView.getFrame());
				} else {
					frame.setVisible(true);
				}
			} catch (IOException e) {
				ErrorFrame ef = new ErrorFrame("ERROR!! Error processing the file");
				ef.setLocationRelativeTo(principalView.getFrame());
			}
			break;

		default:
			analysis = new Analysis(path);
			try {
				num = business.analyzeUrl(analysis);
			} catch (URISyntaxException | IOException e) {
				num = -1;
				ErrorFrame ef = new ErrorFrame("ERROR!! Impossible to connect");
				ef.setLocationRelativeTo(principalView.getFrame());
				return;
			} catch (InterruptedException e1) {
				num = -1;
				ErrorFrame ef = new ErrorFrame("ERROR!! Problem due to download process");
				ef.setLocationRelativeTo(principalView.getFrame());
				return;
			} catch (ImpossibleToConnectException e1) {
				num = -1;
				ErrorFrame ef = new ErrorFrame("ERROR!! Impossible to connect");
				ef.setLocationRelativeTo(principalView.getFrame());
				return;
			}
		}

		// Create the table
		matrix = new String[analysis.getMetadataList().size()][3];
		int i = 0;
		for (Metadata m : analysis.getMetadataList()) {
			matrix[i][0] = m.getKey();
			matrix[i][1] = m.getValue();
			matrix[i][2] = m.getFilePath();
			i++;
		}
		String[] cols = { "Key", "Value", "Path" };
		table = new JTable(matrix, cols);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int j = 0; j < table.getColumnCount(); j++) {
			table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
		}

		table.setDefaultEditor(Object.class, null);
		scroll = new JScrollPane(table);
		frame.getPanel_4().add(scroll, BorderLayout.CENTER);

		// Key filter
		frame.getBtnNewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter(frame.getTextField().getText(), FILTER_TYPE.KEY);
				frame.getTextField().setText("");
			}
		});

		// Value filter
		frame.getBtnNewButton_1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter(frame.getTextField_1().getText(), FILTER_TYPE.VALUE);
				frame.getTextField_1().setText("");
			}
		});

		// Path filter
		frame.getBtnNewButton_2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter(frame.getTextField_2().getText(), FILTER_TYPE.PATH);
				frame.getTextField_2().setText("");
			}
		});

		// Name filter
		frame.getBtnNewButton_3().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter("NAME", FILTER_TYPE.NAME);
			}
		});

		// Email filter
		frame.getBtnNewButton_4().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter("EMAIL", FILTER_TYPE.EMAIL);
			}
		});

		// GPS filter
		frame.getBtnNewButton_5().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filter("GPS", FILTER_TYPE.GPS);
			}
		});

		// Save project
		frame.getBtnNewButton_6().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveView(view);

			}
		});

		// Delete metadata from analysis
		frame.getBtnNewButton_7().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletedView(principalView, path);

			}
		});

	}

	private void filter(String text, FILTER_TYPE type) {
		FilterWithType ft = business.filter(text, type, filtersList, table);
		if (text != null && type != null) {
			addFilterButton(ft, type);
		}

	}

	/**
	 * It is used to add a new button (and an horizontal strut to have space between
	 * buttons) that represents a filter. When you press it, the filter disappears.
	 * 
	 * @param filter The filter that represents the new button.
	 * @param type   The type of filter.
	 */
	private void addFilterButton(FilterWithType filter, FILTER_TYPE type) {
		JButton btn = new JButton(filter.getText());
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Component horizontalStrut = Box.createHorizontalStrut(10);
		btn.addActionListener(new ActionListener() {

			// Remove the button, the filter and the horizontal strut associated when the
			// button is pressed.
			@Override
			public void actionPerformed(ActionEvent e) {
				for (FilterWithType f : filtersList) {
					if (f.getText().equals(btn.getText()) && f.getType() == type) {
						frame.getHorizontalBox_6().remove(btn);
						frame.getHorizontalBox_6().remove(horizontalStrut);
						filtersList.remove(f);
						break;
					}
				}

				filter(null, null);
				frame.revalidate();
				frame.repaint();

			}
		});

		// Add the button and the horizontal strut.
		switch (type) {
		case KEY:
			btn.setBackground(new Color(136, 238, 247));
			break;
		case VALUE:
			btn.setBackground(new Color(135, 248, 186));
			break;
		case PATH:
			btn.setBackground(new Color(208, 249, 134));
			break;
		default:
			btn.setBackground(new Color(250, 221, 133));
		}
		frame.getHorizontalBox_6().add(btn);
		frame.getHorizontalBox_6().add(horizontalStrut);
		btn.setVisible(true);
		frame.revalidate();
		frame.repaint();

	}

	public Analysis getAnalysis() {
		return analysis;
	}

	public int getNum() {
		return num;
	}

	public AnalyzingFrame getFrame() {
		return frame;
	}

	public List<FilterWithType> getFiltersList() {
		return filtersList;
	}

}
