package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel clientPanel;
    private JPanel queuePanel;
    private JPanel simulationPanel;
    private JPanel arrivalMinPanel;
    private JPanel arrivalMaxPanel;
    private JPanel serviceMinPanel;
    private JPanel serviceMaxPanel;

    private JLabel clientLabel;
    private JLabel queueLabel;
    private JLabel simulationLabel;
    private JLabel arrivalMinLabel;
    private JLabel arrivalMaxLabel;
    private JLabel serviceMinLabel;
    private JLabel serviceMaxLabel;

    private JTextField clientText;
    private JTextField queueText;
    private JTextField simulationText;
    private JTextField arrivalMinText;
    private JTextField arrivalMaxText;
    private JTextField serviceMinText;
    private JTextField serviceMaxText;

    private JCheckBox timeCheckBox;
    private JCheckBox shortestCheckBox;
    private JCheckBox fullLogCheckBox;

    private JPanel startPanel;
    private JButton startButton;

    public SimulationFrame() {
        this.setTitle("Simulation Inputs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 300, 360);

        mainPanel = new JPanel();

        clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        clientPanel.setPreferredSize(new Dimension(250, 30));

        clientLabel = new JLabel("Number of clients: ");
        clientLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        clientLabel.setPreferredSize(new Dimension(160, 20));
        clientPanel.add(clientLabel);

        clientText = new JTextField();
        clientText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        clientText.setPreferredSize(new Dimension(60, 20));
        clientPanel.add(clientText);

        queuePanel = new JPanel();
        queuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        queuePanel.setPreferredSize(new Dimension(250, 30));

        queueLabel = new JLabel("Number of queues: ");
        queueLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        queueLabel.setPreferredSize(new Dimension(160, 20));
        queuePanel.add(queueLabel);

        queueText = new JTextField();
        queueText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        queueText.setPreferredSize(new Dimension(60, 20));
        queuePanel.add(queueText);

        simulationPanel = new JPanel();
        simulationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        simulationPanel.setPreferredSize(new Dimension(250, 30));

        simulationLabel = new JLabel("Simulation interval: ");
        simulationLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        simulationLabel.setPreferredSize(new Dimension(160, 20));
        simulationPanel.add(simulationLabel);

        simulationText = new JTextField();
        simulationText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        simulationText.setPreferredSize(new Dimension(60, 20));
        simulationPanel.add(simulationText);

        arrivalMinPanel = new JPanel();
        arrivalMinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        arrivalMinPanel.setPreferredSize(new Dimension(250, 30));

        arrivalMinLabel = new JLabel("Minimum arrival time: ");
        arrivalMinLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        arrivalMinLabel.setPreferredSize(new Dimension(160, 20));
        arrivalMinPanel.add(arrivalMinLabel);

        arrivalMinText = new JTextField();
        arrivalMinText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        arrivalMinText.setPreferredSize(new Dimension(60, 20));
        arrivalMinPanel.add(arrivalMinText);

        arrivalMaxPanel = new JPanel();
        arrivalMaxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        arrivalMaxPanel.setPreferredSize(new Dimension(250, 30));

        arrivalMaxLabel = new JLabel("Maximum arrival time: ");
        arrivalMaxLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        arrivalMaxLabel.setPreferredSize(new Dimension(160, 20));
        arrivalMaxPanel.add(arrivalMaxLabel);

        arrivalMaxText = new JTextField();
        arrivalMaxText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        arrivalMaxText.setPreferredSize(new Dimension(60, 20));
        arrivalMaxPanel.add(arrivalMaxText);

        serviceMinPanel = new JPanel();
        serviceMinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        serviceMinPanel.setPreferredSize(new Dimension(250, 30));

        serviceMinLabel = new JLabel("Minimum service time: ");
        serviceMinLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        serviceMinLabel.setPreferredSize(new Dimension(160, 20));
        serviceMinPanel.add(serviceMinLabel);

        serviceMinText = new JTextField();
        serviceMinText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        serviceMinText.setPreferredSize(new Dimension(60, 20));
        serviceMinPanel.add(serviceMinText);

        serviceMaxPanel = new JPanel();
        serviceMaxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        serviceMaxPanel.setPreferredSize(new Dimension(250, 30));

        serviceMaxLabel = new JLabel("Maximum service time: ");
        serviceMaxLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        serviceMaxLabel.setPreferredSize(new Dimension(160, 20));
        serviceMaxPanel.add(serviceMaxLabel);

        serviceMaxText = new JTextField();
        serviceMaxText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        serviceMaxText.setPreferredSize(new Dimension(60, 20));
        serviceMaxPanel.add(serviceMaxText);

        timeCheckBox = new JCheckBox("Time Strategy");
        shortestCheckBox = new JCheckBox("Shortest Queue Strategy");
        fullLogCheckBox = new JCheckBox("Full Log");

        startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startButton = new JButton("Start");
        startPanel.add(startButton);


        mainPanel.add(clientPanel);
        mainPanel.add(queuePanel);
        mainPanel.add(simulationPanel);
        mainPanel.add(arrivalMinPanel);
        mainPanel.add(arrivalMaxPanel);
        mainPanel.add(serviceMinPanel);
        mainPanel.add(serviceMaxPanel);
        mainPanel.add(timeCheckBox);
        mainPanel.add(shortestCheckBox);
        mainPanel.add(fullLogCheckBox);
        mainPanel.add(startPanel);

        this.setContentPane(mainPanel);
    }

    public String getClient() {
        return this.clientText.getText();
    }

    public String getQueue() {
        return this.queueText.getText();
    }

    public String getSim() {
        return this.simulationText.getText();
    }

    public String getArrivalMin() {
        return this.arrivalMinText.getText();
    }

    public String getArrivalMax() {
        return this.arrivalMaxText.getText();
    }

    public String getServiceMin() {
        return this.serviceMinText.getText();
    }

    public String getServiceMax() {
        return this.serviceMaxText.getText();
    }

    public boolean timeStrategy() {
        return this.timeCheckBox.isSelected();
    }

    public boolean shortestQueueStrategy() {
        return this.shortestCheckBox.isSelected();
    }

    public  boolean fullLog(){
        return this.fullLogCheckBox.isSelected();
    }

    public void startButtonListener(ActionListener actionListener) {
        this.startButton.addActionListener(actionListener);
    }

    public void errorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input problem!", JOptionPane.WARNING_MESSAGE);
    }

}
