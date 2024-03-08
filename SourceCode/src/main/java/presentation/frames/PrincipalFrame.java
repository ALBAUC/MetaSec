package presentation.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Cursor;


public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel principalBackPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;

	/**
	 * Create the frame.
	 */
	public PrincipalFrame() {

		setResizable(false);
		setMaximumSize(new Dimension(1000, 3767));
		setIconImage(new ImageIcon(PrincipalFrame.class.getResource("/images/icon.png")).getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1034, 423);
		principalBackPane = new JPanel();
		principalBackPane.setBackground(new Color(255, 255, 255));
		principalBackPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(principalBackPane);
		principalBackPane.setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(31);
		verticalStrut.setSize(new Dimension(0, 31));
		verticalStrut.setMaximumSize(new Dimension(0, 31));
		principalBackPane.add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(60);
		verticalStrut_1.setPreferredSize(new Dimension(0, 31));
		verticalStrut_1.setMaximumSize(new Dimension(32767, 31));
		verticalStrut_1.setMinimumSize(new Dimension(0, 31));
		principalBackPane.add(verticalStrut_1, BorderLayout.SOUTH);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(50, 0));
		principalBackPane.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setMinimumSize(new Dimension(10, 0));
		horizontalStrut_1.setMaximumSize(new Dimension(10, 32767));
		horizontalStrut_1.setPreferredSize(new Dimension(50, 0));
		principalBackPane.add(horizontalStrut_1, BorderLayout.EAST);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		principalBackPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("M E T A S E C");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Engravers MT", Font.PLAIN, 60));
		panel_1.add(lblNewLabel, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("University of Cantabria. TFG Celso Gimeno Corbella ");
		lblNewLabel_1.setForeground(new Color(212, 212, 212));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component verticalStrut_2 = Box.createVerticalStrut(122);
		verticalStrut_2.setPreferredSize(new Dimension(0, 80));
		panel_2.add(verticalStrut_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(textField);
		textField.setColumns(60);

		btnNewButton = new JButton("...");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnNewButton);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_5, BorderLayout.NORTH);

		btnNewButton_4 = new JButton("Analyze internet");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(btnNewButton_4);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_4);

		btnNewButton_1 = new JButton("Analyze local");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(btnNewButton_1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_2);

		btnNewButton_2 = new JButton("Delete all metadata");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(btnNewButton_2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_3);

		btnNewButton_3 = new JButton("Open project");
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(btnNewButton_3);

		Component verticalStrut_3 = Box.createVerticalStrut(152);
		verticalStrut_3.setPreferredSize(new Dimension(0, 50));
		verticalStrut_3.setBackground(new Color(255, 255, 255));
		panel_4.add(verticalStrut_3, BorderLayout.SOUTH);

		// Center the frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}

	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public JPanel getPrincipalBackPane() {
		return principalBackPane;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

}
