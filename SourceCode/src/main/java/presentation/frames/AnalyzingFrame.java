package presentation.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Cursor;
import javax.swing.JSeparator;


public class AnalyzingFrame extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private Box horizontalBox_6;
	private JButton btnNewButton_6;
	private JPanel panel_3;
	private JPanel panel_8;
	private JButton btnNewButton_7;

	/**
	 * Create the frame.
	 */
	public AnalyzingFrame() {
		setIconImage(new ImageIcon(AnalyzingFrame.class.getResource("/images/icon.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1174, 725);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("M E T A S E C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Engravers MT", Font.PLAIN, 60));
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("University of Cantabria. TFG Celso Gimeno Corbella ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(212, 212, 212));
		panel.add(lblNewLabel_1, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_5);

		Box verticalBox = Box.createVerticalBox();
		panel_5.add(verticalBox);
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		verticalStrut_7.setPreferredSize(new Dimension(0, 10));
		verticalBox.add(verticalStrut_7);
		
		JLabel lblNewLabel_2 = new JLabel("Advanced filters");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(lblNewLabel_2);
		
		Component verticalStrut_8 = Box.createVerticalStrut(20);
		verticalStrut_8.setPreferredSize(new Dimension(0, 2));
		verticalBox.add(verticalStrut_8);
		
		JSeparator separator = new JSeparator();
		verticalBox.add(separator);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		textField = new JTextField();
		horizontalBox.add(textField);
		textField.setColumns(20);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		btnNewButton = new JButton("Key filter");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(136, 238, 247));
		btnNewButton.setPreferredSize(new Dimension(95, 23));
		horizontalBox.add(btnNewButton);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 15));
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		textField_1 = new JTextField();
		horizontalBox_1.add(textField_1);
		textField_1.setColumns(20);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);

		btnNewButton_1 = new JButton("Value filter");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(135, 248, 186));
		btnNewButton_1.setPreferredSize(new Dimension(95, 23));
		horizontalBox_1.add(btnNewButton_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(0, 15));
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		textField_2 = new JTextField();
		horizontalBox_2.add(textField_2);
		textField_2.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_4);

		btnNewButton_2 = new JButton("Path filter");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(new Color(208, 249, 134));
		btnNewButton_2.setPreferredSize(new Dimension(95, 23));
		horizontalBox_2.add(btnNewButton_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_6);

		Box verticalBox_1 = Box.createVerticalBox();
		panel_6.add(verticalBox_1);
		
		Component verticalStrut_9 = Box.createVerticalStrut(20);
		verticalStrut_9.setPreferredSize(new Dimension(0, 10));
		verticalBox_1.add(verticalStrut_9);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setMaximumSize(new Dimension(300, 32767));
		horizontalStrut_5.setPreferredSize(new Dimension(300, 0));
		verticalBox_1.add(horizontalStrut_5);
		
		JLabel lblNewLabel_3 = new JLabel("Predefined filters (not exact)");
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_1.add(lblNewLabel_3);
		
		Component verticalStrut_10 = Box.createVerticalStrut(20);
		verticalStrut_10.setPreferredSize(new Dimension(0, 2));
		verticalBox_1.add(verticalStrut_10);
		
		JSeparator separator_1 = new JSeparator();
		verticalBox_1.add(separator_1);

		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut_4);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_3);

		btnNewButton_3 = new JButton("Name filter");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(new Color(250, 221, 133));
		btnNewButton_3.setMinimumSize(new Dimension(105, 23));
		btnNewButton_3.setMaximumSize(new Dimension(105, 23));
		btnNewButton_3.setPreferredSize(new Dimension(105, 23));
		horizontalBox_3.add(btnNewButton_3);

		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalStrut_5.setPreferredSize(new Dimension(0, 15));
		verticalBox_1.add(verticalStrut_5);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_4);

		btnNewButton_4 = new JButton("Email filter");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setBackground(new Color(250, 221, 133));
		btnNewButton_4.setMaximumSize(new Dimension(105, 23));
		btnNewButton_4.setMinimumSize(new Dimension(105, 23));
		btnNewButton_4.setPreferredSize(new Dimension(105, 23));
		horizontalBox_4.add(btnNewButton_4);

		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalStrut_6.setPreferredSize(new Dimension(0, 15));
		verticalBox_1.add(verticalStrut_6);

		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_5);

		btnNewButton_5 = new JButton("GPS filter");
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setBackground(new Color(250, 221, 133));
		btnNewButton_5.setMinimumSize(new Dimension(105, 23));
		btnNewButton_5.setMaximumSize(new Dimension(105, 23));
		btnNewButton_5.setPreferredSize(new Dimension(105, 23));
		horizontalBox_5.add(btnNewButton_5);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setPreferredSize(new Dimension(10, 60));
		panel_8.setSize(new Dimension(0, 30));
		panel_8.setMinimumSize(new Dimension(10, 30));
		panel_3.add(panel_8, BorderLayout.NORTH);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setPreferredSize(new Dimension(1200, 50));
		verticalBox_2.setMaximumSize(new Dimension(1200, 0));
		verticalBox_2.setMinimumSize(new Dimension(600, 0));
		panel_8.add(verticalBox_2);
		
		JLabel lblNewLabel_4 = new JLabel("Applied filters");
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox_2.add(lblNewLabel_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setMinimumSize(new Dimension(400, 0));
		separator_2.setSize(new Dimension(400, 0));
		verticalBox_2.add(separator_2);
		
		horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_6);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setSize(new Dimension(0, 13));
		panel_7.setMinimumSize(new Dimension(10, 13));
		panel_7.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnNewButton_6 = new JButton("SAVE");
		btnNewButton_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_6.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setBackground(new Color(166, 198, 244));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_7.add(btnNewButton_6);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_6);
		
		btnNewButton_7 = new JButton("DELETE");
		btnNewButton_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_7.setBackground(new Color(166, 198, 244));
		btnNewButton_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_7.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton_7.setForeground(new Color(255, 255, 255));
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_7.add(btnNewButton_7);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setMaximumSize(new Dimension(32767, 5));
		verticalStrut.setMinimumSize(new Dimension(0, 5));
		verticalStrut.setPreferredSize(new Dimension(0, 5));
		contentPane.add(verticalStrut, BorderLayout.SOUTH);
	}


	public JButton getBtnNewButton_7() {
		return btnNewButton_7;
	}


	public JPanel getPanel_8() {
		return panel_8;
	}

	public JPanel getPanel_3() {
		return panel_3;
	}

	public JButton getBtnNewButton_6() {
		return btnNewButton_6;
	}

	public JPanel getPanel_4() {
		return panel_4;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}


	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	public JButton getBtnNewButton_5() {
		return btnNewButton_5;
	}

	public Box getHorizontalBox_6() {
		return horizontalBox_6;
	}

}
