package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SenderView extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField locationTextField;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel locationLabel;
	private JLabel imageLabel;
	private JCheckBox checkButton;
	private JButton nextButton;

	public SenderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 700, 500);
		setTitle("SantaLetterSender");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameLabel = new JLabel("Nume:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(40, 20, 60, 20);
		contentPane.add(nameLabel);

		ageLabel = new JLabel("Varsta:");
		ageLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ageLabel.setBounds(40, 100, 60, 20);
		contentPane.add(ageLabel);

		locationLabel = new JLabel("Locatie:");
		locationLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		locationLabel.setBounds(40, 190, 70, 20);
		contentPane.add(locationLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(150, 20, 140, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		ageTextField = new JTextField();
		ageTextField.setColumns(10);
		ageTextField.setBounds(150, 100, 140, 20);
		contentPane.add(ageTextField);

		locationTextField = new JTextField();
		locationTextField.setColumns(10);
		locationTextField.setBounds(150, 190, 140, 20);
		contentPane.add(locationTextField);

		imageLabel = new JLabel("");
		imageLabel.setBounds(390, 20, 260, 200);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\images\\Christmass.png").getImage()
				.getScaledInstance(260, 200, Image.SCALE_DEFAULT));
		imageLabel.setIcon(imageIcon);
		contentPane.add(imageLabel);

		checkButton = new JCheckBox("Am fost cuminte");
		checkButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkButton.setBounds(280, 330, 150, 20);
		contentPane.add(checkButton);

		nextButton = new JButton("Mai departe");
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		nextButton.setBounds(280, 365, 127, 25);
		contentPane.add(nextButton);

	}

	public String getNameField() {
		return this.nameTextField.getText();
	}

	public String getAgeField() {
		return this.ageTextField.getText();
	}

	public String getLocationField() {
		return this.locationTextField.getText();
	}

	public boolean getIsGood() {
		if (this.checkButton.isSelected())
			return true;
		else
			return false;
	}

	public void nextButtonListener(ActionListener actionListener) {
		this.nextButton.addActionListener(actionListener);
	}

	public void showNameError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void showAgeError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void showLocationError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
