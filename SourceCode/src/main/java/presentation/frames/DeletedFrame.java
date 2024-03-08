package presentation.frames;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Cursor;

public class DeletedFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private Component verticalStrut_2;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public DeletedFrame() {
		
		setIconImage(new ImageIcon(DeletedFrame.class.getResource("/images/icon.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 211);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setAlignmentX(0.5f);
		horizontalBox_1.add(lblNewLabel);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setMaximumSize(new Dimension(32767, 30));
		verticalBox.add(verticalStrut_2);
		
		lblNewLabel_1 = new JLabel("DELETING SENSITIVE METADATA...");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox.add(lblNewLabel_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component glue = Box.createGlue();
		horizontalBox.add(glue);
		
		btnNewButton = new JButton("View remaining metadata");
		btnNewButton.setBorder(new EmptyBorder(6, 15, 6, 15));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setBackground(new Color(166, 198, 244));
		horizontalBox.add(btnNewButton);
		
		Component glue_1 = Box.createGlue();
		horizontalBox.add(glue_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 10));
		verticalStrut_1.setMinimumSize(new Dimension(0, 10));
		contentPane.add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_1, BorderLayout.EAST);

	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public Component getVerticalStrut_2() {
		return verticalStrut_2;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

}
