package presentation.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Cursor;

public class SaveFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public SaveFrame() {
		setResizable(false);
		
		setIconImage(new ImageIcon(SaveFrame.class.getResource("/images/icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 187);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut, BorderLayout.NORTH);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_1, BorderLayout.WEST);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_1, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		lblNewLabel = new JLabel("Name of the new project:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox.add(lblNewLabel);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox.add(textField);
		textField.setColumns(20);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		btnNewButton = new JButton("SAVE");
		btnNewButton.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(166, 198, 244));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		horizontalBox_1.add(btnNewButton);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

}
