package GUI;

import javax.swing.*;
import java.awt.*;

public class SimulationLog extends JFrame {

    private JPanel mainPanel;

    private JTextArea logText;
    private JTextArea clientText;
    private JTextArea resultText;

    private JScrollPane areaScrollPane1, areaScrollPane2;

    public SimulationLog() {
        this.setTitle("Simulation Log");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 100, 600, 700);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);

        logText = new JTextArea();
        logText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        areaScrollPane1 = new JScrollPane(logText);
        areaScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane1.setPreferredSize(new Dimension(150, 640));

        clientText = new JTextArea();
        clientText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        areaScrollPane2 = new JScrollPane(clientText);
        areaScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane2.setPreferredSize(new Dimension(150, 640));

        resultText = new JTextArea();
        resultText.setFont(new Font("Tahoma", Font.BOLD, 13));

        mainPanel.add(areaScrollPane2);
        mainPanel.add(areaScrollPane1);
        mainPanel.add(resultText);

        this.setContentPane(mainPanel);

    }

    public void updateLogText(String str) {
        this.logText.setText(str);
        this.logText.setCaretPosition(this.logText.getDocument().getLength());
    }

    public void updateFullLogText(String str) {
        this.logText.setText(this.logText.getText() + str);
        this.logText.setCaretPosition(this.logText.getDocument().getLength());
    }

    public String getLogText(){
        return this.logText.getText();
    }

    public void updateClientText(String str) {
        this.clientText.setText(str);
    }

    public void updateResultText(String str) {
        this.resultText.setText(str);
        mainPanel.remove(areaScrollPane2);
    }

}
