package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import enums.GiftType;
import model.Gift;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GiftsView extends JFrame {

    private JPanel contentPane;
    private JTextField giftTextField;
    private JLabel giftNameLabel;
    private JLabel giftTypeLabel;
    private JComboBox<GiftType> giftComboBox;
    private JButton addGiftButton;
    private JButton sendLetterButton;
    private JLabel[] giftsLabel = new JLabel[5];

    public GiftsView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 700, 500);
        setTitle("SantaLetterSender");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        giftNameLabel = new JLabel("Nume cadou:");
        giftNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        giftNameLabel.setBounds(50, 50, 120, 20);
        contentPane.add(giftNameLabel);

        giftTextField = new JTextField();
        giftTextField.setBounds(220, 50, 130, 19);
        contentPane.add(giftTextField);
        giftTextField.setColumns(10);

        giftTypeLabel = new JLabel("Tip cadou:");
        giftTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        giftTypeLabel.setBounds(50, 120, 120, 20);
        contentPane.add(giftTypeLabel);

        GiftType[] gifts = {GiftType.CAR, GiftType.DOLL, GiftType.BOARDGAME, GiftType.PCGAME};
        giftComboBox = new JComboBox<GiftType>(gifts);
        giftComboBox.setBounds(220, 120, 130, 20);
        contentPane.add(giftComboBox);

        addGiftButton = new JButton("Adauga Cadou");
        addGiftButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        addGiftButton.setBounds(220, 390, 150, 25);
        contentPane.add(addGiftButton);

        sendLetterButton = new JButton("Trimite");
        sendLetterButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        sendLetterButton.setBounds(400, 390, 150, 25);
        contentPane.add(sendLetterButton);

        addGiftButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addGiftButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addGiftButton.setBackground(null);
            }
        });
        sendLetterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendLetterButton.setBackground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendLetterButton.setBackground(null);
            }
        });
        for (int i = 0; i < 5; i++) {
            giftsLabel[i] = new JLabel("");
            giftsLabel[i].setFont(new Font("Tahoma", Font.BOLD, 14));
            giftsLabel[i].setBounds(400, 40 * (i + 1), 300, 20);
            contentPane.add(giftsLabel[i]);
        }

    }

    public void addGiftButtonListener(ActionListener actionListener) {
        this.addGiftButton.addActionListener(actionListener);
    }

    public void sendLetterButtonListener(ActionListener actionListener) {
        this.sendLetterButton.addActionListener(actionListener);
    }

    public String getGiftName() {
        return this.giftTextField.getText();
    }

    public GiftType getGiftType() {
        return (GiftType) this.giftComboBox.getSelectedItem();
    }

    public void showNameError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void showLimitReachedError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void addGiftsLabel(Set<Gift> gifts) {
        int index = 0;
        for (Gift g : gifts) {
            giftsLabel[index].setText(g.getGiftType() + ": " + g.getName());
            index++;
        }
    }

    public void showSendLetterMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void sendLetterError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
