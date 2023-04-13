package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

/**
 * The View Class which provide all the action which user can do.
 * Contains all the panels we use, getters methods for text fields and also action listeners for our buttons.
 */
public class View extends JFrame {
    private CardLayout crd;

    private Container cPane;

    private JPanel mainPanel;
    private JPanel titlePanel;

    private JPanel clientPanel;

    private JPanel backButtonPanel;
    private JPanel backButtonPanel1;
    private JPanel backButtonPanel2;

    private JPanel clientButtonsPanel;
    private JPanel clientFieldsPanel;
    private JPanel showClientsPanel;

    private JPanel productPanel;
    private JPanel productButtonsPanel;
    private JPanel productFieldsPanel;
    private JPanel showProductsPanel;

    private JPanel orderPanel;
    private JPanel orderButtonsPanel;
    private JPanel orderFieldsPanel;
    private JPanel showOrdersPanel;
    private JPanel showBillsPanel;

    private JPanel backPanelPrevious;
    private JPanel backPanelPrevious1;
    private JPanel backPanelPrevious2;
    private JPanel backPanelPrevious3;

    private JLabel titleLabel;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel adressLabel;

    private JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel stockLabel;

    private JLabel orderIdLabel;
    private JLabel orderClientLabel;
    private JLabel orderProductLabel;
    private JLabel quantityLabel;

    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField adressTextField;

    private JTextField productIdTextField;
    private JTextField productNameTextField;
    private JTextField priceTextField;
    private JTextField stockTextField;

    private JTextField orderIdTextField;
    private JTextField orderClientTextField;
    private JTextField orderProductTextField;
    private JTextField quantityTextField;

    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    private JButton backButton;
    private JButton backButtonPrevious;

    private JButton backButton1;
    private JButton backButtonPrevious1;

    private JButton backButton2;
    private JButton backButtonPrevious2;
    private JButton backButtonPrevious3;

    private JButton addClientButton;
    private JButton showClientButton;
    private JButton removeClientButton;
    private JButton editClientButton;

    private JButton addProductButton;
    private JButton showProductButton;
    private JButton removeProductButton;
    private JButton editProductButton;

    private JButton addOrderButton;
    private JButton removeOrderButton;
    private JButton showClientOrderButton;
    private JButton showProductOrderButton;
    private JButton showOrderButton;
    private JButton showBillButton;

    private JTable table;
    private JTable productTable;
    private JTable orderTable;
    private JTable billTable;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPanel;
    private JScrollPane productTableScrollPanel;
    private JScrollPane orderTableScrollPane;
    private JScrollPane billTableScrollPane;

    public View() {
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(450, 150, 500, 450);

        cPane = this.getContentPane();
        crd = new CardLayout();
        cPane.setLayout(crd);

        //// MAIN PANEL ////

        mainPanel = new JPanel();

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        titlePanel.setPreferredSize(new Dimension(450, 100));
        titleLabel = new JLabel("Select the table you want to work with: ");
        titleLabel.setFont(new Font("Georgia", Font.ITALIC + Font.BOLD, 20));
        titlePanel.add(titleLabel);

        clientButton = new JButton("Clients");
        clientButton.setFont(new Font("Georgia", Font.BOLD, 16));
        clientButton.setPreferredSize(new Dimension(150, 100));
        productButton = new JButton("Products");
        productButton.setFont(new Font("Georgia", Font.BOLD, 16));
        productButton.setPreferredSize(new Dimension(150, 100));
        orderButton = new JButton("Create an order");
        orderButton.setFont(new Font("Georgia", Font.BOLD, 16));
        orderButton.setPreferredSize(new Dimension(225, 100));

        mainPanel.add(titlePanel);
        mainPanel.add(clientButton);
        mainPanel.add(productButton);
        mainPanel.add(orderButton);

        //// CLIENT PANEL ////

        clientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.setPreferredSize(new Dimension(475, 100));
        backButton = new JButton("Back");
        backButton.setFont(new Font("Georgia", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(75, 40));
        backButtonPanel.add(backButton);

        clientButtonsPanel = new JPanel();
        clientButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        clientButtonsPanel.setPreferredSize(new Dimension(480, 130));
        showClientButton = new JButton("View all");
        showClientButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showClientButton.setPreferredSize(new Dimension(100, 40));
        addClientButton = new JButton("Add");
        addClientButton.setFont(new Font("Georgia", Font.BOLD, 14));
        addClientButton.setPreferredSize(new Dimension(100, 40));
        removeClientButton = new JButton("Delete");
        removeClientButton.setFont(new Font("Georgia", Font.BOLD, 14));
        removeClientButton.setPreferredSize(new Dimension(100, 40));
        editClientButton = new JButton("Edit");
        editClientButton.setFont(new Font("Georgia", Font.BOLD, 14));
        editClientButton.setPreferredSize(new Dimension(100, 40));
        clientButtonsPanel.add(showClientButton);
        clientButtonsPanel.add(addClientButton);
        clientButtonsPanel.add(removeClientButton);
        clientButtonsPanel.add(editClientButton);

        clientFieldsPanel = new JPanel();
        clientFieldsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
        clientFieldsPanel.setPreferredSize(new Dimension(400, 215));

        idLabel = new JLabel("Id: ");
        idLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        idLabel.setPreferredSize(new Dimension(60, 20));
        clientFieldsPanel.add(idLabel);
        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        idTextField.setPreferredSize(new Dimension(150, 20));
        clientFieldsPanel.add(idTextField);

        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        nameLabel.setPreferredSize(new Dimension(60, 20));
        clientFieldsPanel.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nameTextField.setPreferredSize(new Dimension(150, 20));
        clientFieldsPanel.add(nameTextField);

        ageLabel = new JLabel("Age: ");
        ageLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        ageLabel.setPreferredSize(new Dimension(60, 20));
        clientFieldsPanel.add(ageLabel);
        ageTextField = new JTextField();
        ageTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ageTextField.setPreferredSize(new Dimension(150, 20));
        clientFieldsPanel.add(ageTextField);

        adressLabel = new JLabel("Adress: ");
        adressLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        adressLabel.setPreferredSize(new Dimension(60, 20));
        clientFieldsPanel.add(adressLabel);
        adressTextField = new JTextField();
        adressTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        adressTextField.setPreferredSize(new Dimension(150, 20));
        clientFieldsPanel.add(adressTextField);

        clientPanel.add(clientButtonsPanel);
        clientPanel.add(clientFieldsPanel);
        clientPanel.add(backButtonPanel);

        showClientsPanel = new JPanel();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableScrollPanel = new JScrollPane(table);
        tableScrollPanel.setPreferredSize(new Dimension(460, 350));
        showClientsPanel.add(tableScrollPanel);

        backPanelPrevious = new JPanel();
        backPanelPrevious.setLayout(new FlowLayout(FlowLayout.CENTER));
        backPanelPrevious.setPreferredSize(new Dimension(480, 100));
        backButtonPrevious = new JButton("Back");
        backButtonPrevious.setFont(new Font("Georgia", Font.BOLD, 14));
        backButtonPrevious.setPreferredSize(new Dimension(75, 40));
        backPanelPrevious.add(backButtonPrevious);
        showClientsPanel.add(backPanelPrevious);

        //// PRODUCT PANEL ////

        productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButtonPanel1 = new JPanel();
        backButtonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel1.setPreferredSize(new Dimension(475, 100));
        backButton1 = new JButton("Back");
        backButton1.setFont(new Font("Georgia", Font.BOLD, 14));
        backButton1.setPreferredSize(new Dimension(75, 40));
        backButtonPanel1.add(backButton1);

        productButtonsPanel = new JPanel();
        productButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        productButtonsPanel.setPreferredSize(new Dimension(480, 130));
        showProductButton = new JButton("View all");
        showProductButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showProductButton.setPreferredSize(new Dimension(100, 40));
        addProductButton = new JButton("Add");
        addProductButton.setFont(new Font("Georgia", Font.BOLD, 14));
        addProductButton.setPreferredSize(new Dimension(100, 40));
        removeProductButton = new JButton("Delete");
        removeProductButton.setFont(new Font("Georgia", Font.BOLD, 14));
        removeProductButton.setPreferredSize(new Dimension(100, 40));
        editProductButton = new JButton("Edit");
        editProductButton.setFont(new Font("Georgia", Font.BOLD, 14));
        editProductButton.setPreferredSize(new Dimension(100, 40));
        productButtonsPanel.add(showProductButton);
        productButtonsPanel.add(addProductButton);
        productButtonsPanel.add(removeProductButton);
        productButtonsPanel.add(editProductButton);

        productFieldsPanel = new JPanel();
        productFieldsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
        productFieldsPanel.setPreferredSize(new Dimension(400, 215));

        productIdLabel = new JLabel("Id: ");
        productIdLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        productIdLabel.setPreferredSize(new Dimension(60, 20));
        productFieldsPanel.add(productIdLabel);
        productIdTextField = new JTextField();
        productIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        productIdTextField.setPreferredSize(new Dimension(150, 20));
        productFieldsPanel.add(productIdTextField);

        productNameLabel = new JLabel("Name: ");
        productNameLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        productNameLabel.setPreferredSize(new Dimension(60, 20));
        productFieldsPanel.add(productNameLabel);
        productNameTextField = new JTextField();
        productNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        productNameTextField.setPreferredSize(new Dimension(150, 20));
        productFieldsPanel.add(productNameTextField);

        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        priceLabel.setPreferredSize(new Dimension(60, 20));
        productFieldsPanel.add(priceLabel);
        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        priceTextField.setPreferredSize(new Dimension(150, 20));
        productFieldsPanel.add(priceTextField);

        stockLabel = new JLabel("Stock: ");
        stockLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        stockLabel.setPreferredSize(new Dimension(60, 20));
        productFieldsPanel.add(stockLabel);
        stockTextField = new JTextField();
        stockTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        stockTextField.setPreferredSize(new Dimension(150, 20));
        productFieldsPanel.add(stockTextField);

        productPanel.add(productButtonsPanel);
        productPanel.add(productFieldsPanel);
        productPanel.add(backButtonPanel1);

        showProductsPanel = new JPanel();
        tableModel = new DefaultTableModel();
        productTable = new JTable(tableModel);
        productTableScrollPanel = new JScrollPane(productTable);
        productTableScrollPanel.setPreferredSize(new Dimension(460, 350));
        showProductsPanel.add(productTableScrollPanel);

        backPanelPrevious1 = new JPanel();
        backPanelPrevious1.setLayout(new FlowLayout(FlowLayout.CENTER));
        backPanelPrevious1.setPreferredSize(new Dimension(480, 100));
        backButtonPrevious1 = new JButton("Back");
        backButtonPrevious1.setFont(new Font("Georgia", Font.BOLD, 14));
        backButtonPrevious1.setPreferredSize(new Dimension(75, 40));
        backPanelPrevious1.add(backButtonPrevious1);
        showProductsPanel.add(backPanelPrevious1);

        //// Order Panel ////

        orderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButtonPanel2 = new JPanel();
        backButtonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel2.setPreferredSize(new Dimension(475, 100));
        backButton2 = new JButton("Back");
        backButton2.setFont(new Font("Georgia", Font.BOLD, 14));
        backButton2.setPreferredSize(new Dimension(75, 40));
        backButtonPanel2.add(backButton2);

        orderButtonsPanel = new JPanel();
        orderButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        orderButtonsPanel.setPreferredSize(new Dimension(480, 130));
        showOrderButton = new JButton("View all");
        showOrderButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showOrderButton.setPreferredSize(new Dimension(125, 40));
        addOrderButton = new JButton("Place order");
        addOrderButton.setFont(new Font("Georgia", Font.BOLD, 14));
        addOrderButton.setPreferredSize(new Dimension(125, 40));
        removeOrderButton = new JButton("Delete");
        removeOrderButton.setFont(new Font("Georgia", Font.BOLD, 14));
        removeOrderButton.setPreferredSize(new Dimension(125, 40));
        showClientOrderButton = new JButton("Clients");
        showClientOrderButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showClientOrderButton.setPreferredSize(new Dimension(125, 40));
        showProductOrderButton = new JButton("Products");
        showProductOrderButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showProductOrderButton.setPreferredSize(new Dimension(125, 40));
        showBillButton = new JButton("Bills");
        showBillButton.setFont(new Font("Georgia", Font.BOLD, 14));
        showBillButton.setPreferredSize(new Dimension(125, 40));
        orderButtonsPanel.add(showOrderButton);
        orderButtonsPanel.add(addOrderButton);
        orderButtonsPanel.add(removeOrderButton);
        orderButtonsPanel.add(showClientOrderButton);
        orderButtonsPanel.add(showProductOrderButton);
        orderButtonsPanel.add(showBillButton);

        orderFieldsPanel = new JPanel();
        orderFieldsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
        orderFieldsPanel.setPreferredSize(new Dimension(400, 215));

        orderIdLabel = new JLabel("Id: ");
        orderIdLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        orderIdLabel.setPreferredSize(new Dimension(75, 20));
        orderFieldsPanel.add(orderIdLabel);
        orderIdTextField = new JTextField();
        orderIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        orderIdTextField.setPreferredSize(new Dimension(150, 20));
        orderFieldsPanel.add(orderIdTextField);

        orderClientLabel = new JLabel("Client id: ");
        orderClientLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        orderClientLabel.setPreferredSize(new Dimension(75, 20));
        orderFieldsPanel.add(orderClientLabel);
        orderClientTextField = new JTextField();
        orderClientTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        orderClientTextField.setPreferredSize(new Dimension(150, 20));
        orderFieldsPanel.add(orderClientTextField);

        orderProductLabel = new JLabel("Product id: ");
        orderProductLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        orderProductLabel.setPreferredSize(new Dimension(75, 20));
        orderFieldsPanel.add(orderProductLabel);
        orderProductTextField = new JTextField();
        orderProductTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        orderProductTextField.setPreferredSize(new Dimension(150, 20));
        orderFieldsPanel.add(orderProductTextField);

        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        quantityLabel.setPreferredSize(new Dimension(75, 20));
        orderFieldsPanel.add(quantityLabel);
        quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        quantityTextField.setPreferredSize(new Dimension(150, 20));
        orderFieldsPanel.add(quantityTextField);

        orderPanel.add(orderButtonsPanel);
        orderPanel.add(orderFieldsPanel);
        orderPanel.add(backButtonPanel2);

        showOrdersPanel = new JPanel();
        tableModel = new DefaultTableModel();
        orderTable = new JTable(tableModel);
        orderTableScrollPane = new JScrollPane(orderTable);
        orderTableScrollPane.setPreferredSize(new Dimension(460, 350));
        showOrdersPanel.add(orderTableScrollPane);

        backPanelPrevious2 = new JPanel();
        backPanelPrevious2.setLayout(new FlowLayout(FlowLayout.CENTER));
        backPanelPrevious2.setPreferredSize(new Dimension(480, 100));
        backButtonPrevious2 = new JButton("Back");
        backButtonPrevious2.setFont(new Font("Georgia", Font.BOLD, 14));
        backButtonPrevious2.setPreferredSize(new Dimension(75, 40));
        backPanelPrevious2.add(backButtonPrevious2);
        showOrdersPanel.add(backPanelPrevious2);

        showBillsPanel = new JPanel();
        tableModel = new DefaultTableModel();
        billTable = new JTable(tableModel);
        billTableScrollPane = new JScrollPane(billTable);
        billTableScrollPane.setPreferredSize(new Dimension(460, 350));
        showBillsPanel.add(billTableScrollPane);

        backPanelPrevious3 = new JPanel();
        backPanelPrevious3.setLayout(new FlowLayout(FlowLayout.CENTER));
        backPanelPrevious3.setPreferredSize(new Dimension(480, 100));
        backButtonPrevious3 = new JButton("Back");
        backButtonPrevious3.setFont(new Font("Georgia", Font.BOLD, 14));
        backButtonPrevious3.setPreferredSize(new Dimension(75, 40));
        backPanelPrevious3.add(backButtonPrevious3);
        showBillsPanel.add(backPanelPrevious3);

        cPane.add("MainPanel", mainPanel);
        cPane.add("ClientPanel", clientPanel);
        cPane.add("ShowClients", showClientsPanel);
        cPane.add("ProductPanel", productPanel);
        cPane.add("ShowProducts", showProductsPanel);
        cPane.add("OrderPanel", orderPanel);
        cPane.add("ShowOrders", showOrdersPanel);
        cPane.add("ShowBills", showBillsPanel);

    }

    public void clientButtonListener(ActionListener actionListener) {
        this.clientButton.addActionListener(actionListener);
    }

    public void productButtonListener(ActionListener actionListener) {
        this.productButton.addActionListener(actionListener);
    }

    public void orderButtonListener(ActionListener actionListener) {
        this.orderButton.addActionListener(actionListener);
    }

    public void backButtonListener(ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    public void backButtonListener1(ActionListener actionListener) {
        this.backButton1.addActionListener(actionListener);
    }

    public void backButtonListener2(ActionListener actionListener) {
        this.backButton2.addActionListener(actionListener);
    }

    public void backButtonPreviousListener(ActionListener actionListener) {
        this.backButtonPrevious.addActionListener(actionListener);
    }

    public void backButtonPreviousListener1(ActionListener actionListener) {
        this.backButtonPrevious1.addActionListener(actionListener);
    }

    public void backButtonPreviousListener2(ActionListener actionListener) {
        this.backButtonPrevious2.addActionListener(actionListener);
    }

    public void backButtonPreviousListener3(ActionListener actionListener) {
        this.backButtonPrevious3.addActionListener(actionListener);
    }

    public void changeFrame(String name) {
        this.crd.show(cPane, name);
    }

    public void changeFramePrevious() {
        this.crd.previous(cPane);
    }

    public void showClientButtonListener(ActionListener actionListener) {
        this.showClientButton.addActionListener(actionListener);
    }

    public void showProductButtonListener(ActionListener actionListener) {
        this.showProductButton.addActionListener(actionListener);
    }

    public void showOrderButtonListener(ActionListener actionListener) {
        this.showOrderButton.addActionListener(actionListener);
    }

    public void showBillButtonListener(ActionListener actionListener) {
        this.showBillButton.addActionListener(actionListener);
    }

    //// Table initialization ////

    /**
     * Generic method to set up the table we want to show in Frame using reflexion
     *
     * @param list list of objects which data we want to display
     */
    public void setUpTable(List<Object> list) {
        Class<?> type = list.get(0).getClass();
        DefaultTableModel tableModel = this.tableModel;
        int size = 0;
        for (Field field : type.getDeclaredFields())
            size++;
        String[] columns = new String[size];
        int i = 0;
        for (Field field : type.getDeclaredFields()) {
            columns[i] = field.getName();
            i++;
        }
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        for (Object obj : list) {
            i = 0;
            Class<?> classType = obj.getClass();
            String[] data = new String[size];
            for (Field field : classType.getDeclaredFields()) {
                Object value;
                field.setAccessible(true);
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                data[i] = String.valueOf(value);
                i++;
            }
            tableModel.addRow(data);
        }
        this.table.setModel(tableModel);
        this.productTable.setModel(tableModel);
        this.orderTable.setModel(tableModel);
    }

    public String getClientId() {
        return this.idTextField.getText();
    }

    public String getClientName() {
        return this.nameTextField.getText();
    }

    public String getClientAge() {
        return this.ageTextField.getText();
    }

    public String getClientAdress() {
        return this.adressTextField.getText();
    }

    public String getProductId() {
        return this.productIdTextField.getText();
    }

    public String getProductName() {
        return this.productNameTextField.getText();
    }

    public String getProductPrice() {
        return this.priceTextField.getText();
    }

    public String getProductStock() {
        return this.stockTextField.getText();
    }

    public String getOrderId() {
        return this.orderIdTextField.getText();
    }

    public String getClientOrder() {
        return this.orderClientTextField.getText();
    }

    public String getProductOrder() {
        return this.orderProductTextField.getText();
    }

    public String getQuantity() {
        return this.quantityTextField.getText();
    }

    public void addClientButtonListener(ActionListener actionListener) {
        this.addClientButton.addActionListener(actionListener);
    }

    public void errorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input problem!", JOptionPane.WARNING_MESSAGE);
    }

    public void successMessage() {
        JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void removeClientButtonListener(ActionListener actionListener) {
        this.removeClientButton.addActionListener(actionListener);
    }

    public void clearTextFields() {
        this.idTextField.setText("");
        this.nameTextField.setText("");
        this.ageTextField.setText("");
        this.adressTextField.setText("");
        this.productIdTextField.setText("");
        this.productNameTextField.setText("");
        this.priceTextField.setText("");
        this.stockTextField.setText("");
        this.orderIdTextField.setText("");
        this.orderClientTextField.setText("");
        this.orderProductTextField.setText("");
        this.quantityTextField.setText("");
    }

    public void editClientButtonListener(ActionListener actionListener) {
        this.editClientButton.addActionListener(actionListener);
    }

    public void addProductButtonListener(ActionListener actionListener) {
        this.addProductButton.addActionListener(actionListener);
    }

    public void removeProductButtonListener(ActionListener actionListener) {
        this.removeProductButton.addActionListener(actionListener);
    }

    public void editProductButtonListener(ActionListener actionListener) {
        this.editProductButton.addActionListener(actionListener);
    }

    public void showClientOrderButtonListener(ActionListener actionListener) {
        this.showClientOrderButton.addActionListener(actionListener);
    }

    public void showProductOrderButtonListener(ActionListener actionListener) {
        this.showProductOrderButton.addActionListener(actionListener);
    }

    public void addOrderButtonListener(ActionListener actionListener) {
        this.addOrderButton.addActionListener(actionListener);
    }

    public void removeOrderButtonListener(ActionListener actionListener) {
        this.removeOrderButton.addActionListener(actionListener);
    }

}
