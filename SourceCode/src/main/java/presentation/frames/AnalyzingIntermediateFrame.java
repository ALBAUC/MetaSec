package presentation.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class AnalyzingIntermediateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;


	/**
	 * Create the frame.
	 */
	public AnalyzingIntermediateFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 175);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		lblNewLabel = new JLabel("ANALYZING...");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		verticalBox.add(lblNewLabel);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		btnNewButton = new JButton("View now");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setVisible(false);
		btnNewButton.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(166, 198, 244));
		horizontalBox.add(btnNewButton);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);
		
		btnNewButton_1 = new JButton("View later");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBackground(new Color(166, 198, 244));
		horizontalBox.add(btnNewButton_1);
	}


	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}


	public JButton getBtnNewButton() {
		return btnNewButton;
	}


	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

}
