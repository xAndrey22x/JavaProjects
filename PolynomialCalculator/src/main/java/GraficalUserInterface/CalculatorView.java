package GraficalUserInterface;

import DataModels.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculatorView extends JFrame {

    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel examplePanel;
    private JPanel input1Panel;
    private JPanel input2Panel;
    private JPanel buttonsPanel;

    private JLabel titleLabel;
    private JLabel exampleLabel;
    private JLabel firstPolynomLabel;
    private JLabel secondPolynomLabel;

    private JTextField firstPolynomText;
    private JTextField secondPolynomText;

    private JButton additionButton;
    private JButton subtractionButton;
    private JButton multiplicationButton;
    private JButton divisionButton;
    private JButton derivativeButton;
    private JButton integralButton;

    private JCheckBox firstPolyCheckBox;
    private JCheckBox secondPolyCheckBox;

    public CalculatorView() {
        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 150, 400, 450);

        mainPanel = new JPanel();

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setPreferredSize(new Dimension(400, 60));
        titleLabel = new JLabel("Polynomial Calculator");
        titleLabel.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        titlePanel.add(titleLabel);

        examplePanel = new JPanel();
        examplePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        examplePanel.setPreferredSize(new Dimension(385, 40));
        exampleLabel = new JLabel("Input form: +2x^5+4x^3-7x^2+3x+2");
        exampleLabel.setFont(new Font("Georgia", Font.ITALIC, 14));
        examplePanel.add(exampleLabel);

        input1Panel = new JPanel();
        input1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        input1Panel.setPreferredSize(new Dimension(380, 25));
        firstPolynomLabel = new JLabel("First polynomial = ");
        firstPolynomLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        firstPolynomLabel.setPreferredSize(new Dimension(138, 20));
        input1Panel.add(firstPolynomLabel);

        firstPolynomText = new JTextField();
        firstPolynomText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        firstPolynomText.setPreferredSize(new Dimension(200, 20));
        input1Panel.add(firstPolynomText);

        firstPolyCheckBox = new JCheckBox();
        input1Panel.add(firstPolyCheckBox);

        input2Panel = new JPanel();
        input2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        input2Panel.setPreferredSize(new Dimension(380, 50));
        secondPolynomLabel = new JLabel("Second polynomial = ");
        secondPolynomLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        input2Panel.add(secondPolynomLabel);

        secondPolynomText = new JTextField();
        secondPolynomText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        secondPolynomText.setPreferredSize(new Dimension(200, 20));
        input2Panel.add(secondPolynomText);

        secondPolyCheckBox = new JCheckBox();
        input2Panel.add(secondPolyCheckBox);

        additionButton = new JButton("Add");
        subtractionButton = new JButton("Subtract");
        multiplicationButton = new JButton("Multiplicate");
        divisionButton = new JButton("Divide");
        derivativeButton = new JButton("Derivative");
        integralButton = new JButton("Integrate");

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2, 2, 2));
        buttonsPanel.add(additionButton);
        buttonsPanel.add(subtractionButton);
        buttonsPanel.add(multiplicationButton);
        buttonsPanel.add(divisionButton);
        buttonsPanel.add(derivativeButton);
        buttonsPanel.add(integralButton);
        buttonsPanel.setPreferredSize(new Dimension(380, 210));

        mainPanel.add(titlePanel);
        mainPanel.add(examplePanel);
        mainPanel.add(input1Panel);
        mainPanel.add(input2Panel);
        mainPanel.add(buttonsPanel);
        this.setContentPane(mainPanel);
    }

    public String getFirstPoly() {
        return this.firstPolynomText.getText();
    }

    public String getSecondPoly() {
        return this.secondPolynomText.getText();
    }

    public boolean isFirstPolyCheck() {
        return this.firstPolyCheckBox.isSelected();
    }

    public boolean isSecondPolyCheck() {
        return this.secondPolyCheckBox.isSelected();
    }

    public void addButtonListener(ActionListener actionListener) {
        this.additionButton.addActionListener(actionListener);
    }

    public void subtractButtonListener(ActionListener actionListener) {
        this.subtractionButton.addActionListener(actionListener);
    }

    public void multiplicationButtonListener(ActionListener actionListener) {
        this.multiplicationButton.addActionListener(actionListener);
    }

    public void divideButtonListener(ActionListener actionListener) {
        this.divisionButton.addActionListener(actionListener);
    }

    public void derivativeButtonListener(ActionListener actionListener) {
        this.derivativeButton.addActionListener(actionListener);
    }

    public void integralButtonListener(ActionListener actionListener) {
        this.integralButton.addActionListener(actionListener);
    }

    public void errorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input problem!", JOptionPane.WARNING_MESSAGE);
    }

    public void resultMessage(Polynomial p) {
        JOptionPane.showMessageDialog(this, "Result: " + p, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resultMessageDivide(List<Polynomial> qr) {
        JOptionPane.showMessageDialog(this, "Result: \n" + "Quotient: " + qr.get(0) +
                "\nRemainder: " + qr.get(1), "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resultMessageIntegrate(Polynomial p) {
        JOptionPane.showMessageDialog(this, "Result: " + p + " +C", "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
