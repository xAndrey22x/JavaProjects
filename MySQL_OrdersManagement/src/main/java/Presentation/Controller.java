package Presentation;

import BusinessLogic.BillBLL;
import BusinessLogic.ClientBLL;
import BusinessLogic.OrderPBLL;
import BusinessLogic.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.OrderP;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for our View
 */
public class Controller {

    private View view;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderPBLL orderPBLL;
    private BillBLL billBLL;
    private String currentPanel = "Start";

    /**
     * Action Listeners for all the buttons we use
     * @param view the View we use
     */
    public Controller(View view) {
        this.view = view;
        this.clientBLL = new ClientBLL();
        clientBLL.findAllClients();
        this.productBLL = new ProductBLL();
        this.orderPBLL = new OrderPBLL();
        this.billBLL = new BillBLL();
        this.view.backButtonListener(new operationsPerformedChangeFrame("MainPanel"));
        this.view.backButtonListener1(new operationsPerformedChangeFrame("MainPanel"));
        this.view.backButtonListener2(new operationsPerformedChangeFrame("MainPanel"));
        this.view.clientButtonListener(new operationsPerformedChangeFrame("ClientPanel"));
        this.view.showClientButtonListener(new operationsPerformedChangeFrame("ShowClients"));
        this.view.showProductButtonListener(new operationsPerformedChangeFrame("ShowProducts"));
        this.view.showOrderButtonListener(new operationsPerformedChangeFrame("ShowOrders"));
        this.view.backButtonPreviousListener(new operationsPerformedChangeFrame("Back"));
        this.view.backButtonPreviousListener1(new operationsPerformedChangeFrame("Back"));
        this.view.backButtonPreviousListener2(new operationsPerformedChangeFrame("Back"));
        this.view.backButtonPreviousListener3(new operationsPerformedChangeFrame("BackOrder"));
        this.view.productButtonListener(new operationsPerformedChangeFrame("ProductPanel"));
        this.view.orderButtonListener(new operationsPerformedChangeFrame("OrderPanel"));
        this.view.showClientOrderButtonListener(new operationsPerformedChangeFrame("ShowClients1"));
        this.view.showProductOrderButtonListener(new operationsPerformedChangeFrame("ShowProducts1"));
        this.view.showBillButtonListener(new operationsPerformedChangeFrame("ShowBills"));
        this.view.addClientButtonListener(new operationsPerformedUpdateTable("AddClient"));
        this.view.removeClientButtonListener(new operationsPerformedUpdateTable("RemoveClient"));
        this.view.editClientButtonListener(new operationsPerformedUpdateTable("UpdateClient"));
        this.view.addProductButtonListener(new operationsPerformedUpdateTable("AddProduct"));
        this.view.removeProductButtonListener(new operationsPerformedUpdateTable("RemoveProduct"));
        this.view.editProductButtonListener(new operationsPerformedUpdateTable("UpdateProduct"));
        this.view.removeOrderButtonListener(new operationsPerformedUpdateTable("RemoveOrder"));
        this.view.addOrderButtonListener(new operationsPerformedUpdateTable("AddOrder"));
    }

    /**
     * The Class to update fields in a table corresponding to the button which is pressed
     */
    class operationsPerformedUpdateTable implements ActionListener {

        private String action;

        public operationsPerformedUpdateTable(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (this.action) {
                case "AddClient":
                    Client c;
                    try {
                        c = checkClient();
                        clientBLL.insertClient(c);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "RemoveClient":
                    try {
                        int id = checkRemoveId();
                        clientBLL.deleteClient(id);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "UpdateClient":
                    try {
                        int id = checkRemoveId();
                        try {
                            int age;
                            if (view.getClientAge().length() != 0) {
                                age = Integer.parseInt(view.getClientAge());
                                if (age <= 0)
                                    throw new Exception("Age must be positive!");
                            }
                            String[] newFields = {view.getClientName(), view.getClientAge(), view.getClientAdress()};
                            clientBLL.updateClient(newFields, id);
                            view.successMessage();
                            view.clearTextFields();
                        }catch(NumberFormatException e1){
                            view.errorMessage("Age must be a number and positive!");
                        }
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "AddProduct":
                    Product p;
                    try {
                        p = checkProduct();
                        productBLL.insertProduct(p);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "RemoveProduct":
                    try {
                        int id = checkRemoveIdProduct();
                        productBLL.deleteProduct(id);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "UpdateProduct":
                    try {
                        int id = checkRemoveIdProduct();
                        try {
                            int stock, price;
                            if (view.getProductPrice().length() != 0) {
                                price = Integer.parseInt(view.getProductPrice());
                                if (price <= 0)
                                    throw new Exception("Price must be positive!");
                            }
                            if (view.getProductStock().length() != 0) {
                                stock = Integer.parseInt(view.getProductStock());
                                if (stock <= 0)
                                    throw new Exception("Stock must be positive!");
                            }
                            String[] newFields = {view.getProductName(), view.getProductPrice(), view.getProductStock()};
                            productBLL.updateProduct(newFields, id);
                            view.successMessage();
                            view.clearTextFields();
                        }catch(NumberFormatException e1){
                            view.errorMessage("Stock and price must be a numbers and positive!");
                        }
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "RemoveOrder":
                    try {
                        int id = checkRemoveIdOrder();
                        orderPBLL.deleteOrder(id);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                case "AddOrder":
                    OrderP o;
                    try {
                        o = checkOrder();
                        orderPBLL.insertOrder(o);
                        view.successMessage();
                        view.clearTextFields();
                    } catch (Exception ex) {
                        view.errorMessage(ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * The class to change the Frame of our View corresponding to the button which is pressed
     */
    class operationsPerformedChangeFrame implements ActionListener {

        private String panelName;

        public operationsPerformedChangeFrame(String name) {
            this.panelName = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((currentPanel.equals("ShowClients1") || currentPanel.equals("ShowProducts1")) && (this.panelName.equals("Back"))) {
                view.changeFrame("OrderPanel");
                return;
            }
            currentPanel = this.panelName;
            if (this.panelName.equals("Back"))
                view.changeFramePrevious();
            else {
                if (this.panelName.equals("BackOrder")) {
                    view.changeFrame("OrderPanel");
                    return;
                }
                if (this.panelName.equals("ShowClients") || this.panelName.equals("ShowClients1")) {
                    List<Client> clientList;
                    clientList = clientBLL.findAllClients();
                    List<Object> list = new ArrayList<>(clientList);
                    if (list.size() == 0) {
                        view.errorMessage("Table is empty!");
                        return;
                    }
                    view.setUpTable(list);
                }
                if (this.panelName.equals("ShowProducts") || this.panelName.equals("ShowProducts1")){
                    List<Product> productList;
                    productList = productBLL.findAllProducts();
                    List<Object> list = new ArrayList<>(productList);
                    if (list.size() == 0) {
                        view.errorMessage("Table is empty!");
                        return;
                    }
                    view.setUpTable(list);
                }
                if (this.panelName.equals("ShowOrders")){
                    List<OrderP> orderList;
                    orderList = orderPBLL.findAllOrders();
                    List<Object> list = new ArrayList<>(orderList);
                    if (list.size() == 0) {
                        view.errorMessage("Table is empty!");
                        return;
                    }
                    view.setUpTable(list);
                }
                if (this.panelName.equals("ShowBills")){
                    List<Bill> billList;
                    billList = billBLL.findAllBills();
                    List<Object> list = new ArrayList<>(billList);
                    if (list.size() == 0) {
                        view.errorMessage("Table is empty!");
                        return;
                    }
                    view.setUpTable(list);
                }
                if (this.panelName.equals("ShowClients1"))
                    view.changeFrame("ShowClients");
                else if (this.panelName.equals("ShowProducts1"))
                    view.changeFrame("ShowProducts");
                else view.changeFrame(this.panelName);
            }
        }
    }

    /**
     * Method to check when we want to delete a Client
     * @return id to be deleted
     * @throws Exception if any fields are wrong
     */
    public int checkRemoveId() throws Exception {
        int id;
        try {
            id = Integer.parseInt(view.getClientId());
        } catch (NumberFormatException e) {
            throw new Exception("Id must be number!");
        }
        if (id <= 0)
            throw new Exception("Number must be positive!");
        List<Client> clientList;
        clientList = clientBLL.findAllClients();
        boolean k = false;
        for (Client c : clientList)
            if (c.getId() == id)
                k = true;
        if (!k)
            throw new Exception("Id doesn't exist!");
        return id;
    }

    /**
     * Method to check when we want to delete a Product
     * @return id to be deleted
     * @throws Exception if any fields are wrong
     */
    public int checkRemoveIdProduct() throws Exception {
        int id;
        try {
            id = Integer.parseInt(view.getProductId());
        } catch (NumberFormatException e) {
            throw new Exception("Id must be number!");
        }
        if (id <= 0)
            throw new Exception("Number must be positive!");
        List<Product> productList;
        productList = productBLL.findAllProducts();
        boolean k = false;
        for (Product p : productList)
            if (p.getId() == id)
                k = true;
        if (!k)
            throw new Exception("Id doesn't exist!");
        return id;
    }

    /**
     * Method to check when we want to delete an Order
     * @return id to be deleted
     * @throws Exception if any fields are wrong
     */
    public int checkRemoveIdOrder() throws Exception {
        int id;
        try {
            id = Integer.parseInt(view.getOrderId());
        } catch (NumberFormatException e) {
            throw new Exception("Id must be number!");
        }
        if (id <= 0)
            throw new Exception("Number must be positive!");
        List<OrderP> orderPList;
        orderPList = orderPBLL.findAllOrders();
        boolean k = false;
        for (OrderP o : orderPList)
            if (o.getId() == id)
                k = true;
        if (!k)
            throw new Exception("Id doesn't exist!");
        billBLL.deleteBill(id);
        return id;
    }

    /**
     * Method to check when we want to insert a Client
     * @return Client to be inserted
     * @throws Exception if any fields are wrong
     */
    public Client checkClient() throws Exception {
        List<Client> clientList;
        clientList = clientBLL.findAllClients();
        int id, age;
        if (view.getClientName().length() == 0 || view.getClientAdress().length() == 0)
            throw new Exception("Fields must be completed!");
        try {
            id = Integer.parseInt(view.getClientId());
            age = Integer.parseInt(view.getClientAge());
        } catch (NumberFormatException e) {
            throw new Exception("Id and Age must be numbers!");
        }
        if (id <= 0 || age <= 0)
            throw new Exception("Numbers must be positive!");
        Client client = new Client(id, view.getClientName(), age, view.getClientAdress());
        for (Client c : clientList)
            if (c.getId() == id)
                throw new Exception("This id already exists!");
        return client;
    }

    /**
     * Method to check when we want to insert a Product
     * @return Product to be inserted
     * @throws Exception if any fields are wrong
     */
    public Product checkProduct() throws Exception {
        List<Product> productList;
        productList = productBLL.findAllProducts();
        int id, price, stock;
        if (view.getProductName().length() == 0)
            throw new Exception("Fields must be completed!");
        try {
            id = Integer.parseInt(view.getProductId());
            price = Integer.parseInt(view.getProductPrice());
            stock = Integer.parseInt(view.getProductStock());
        } catch (NumberFormatException e) {
            throw new Exception("Id, price and stock must be numbers!");
        }
        if (id <= 0 || price <= 0 || stock <= 0)
            throw new Exception("Numbers must be positive!");
        Product product = new Product(id, view.getProductName(), price, stock);
        for (Product p : productList)
            if (p.getId() == id)
                throw new Exception("This id already exists!");
        return product;
    }

    /**
     * Method to check when we want to insert an Order
     * @return Order to be inserted
     * @throws Exception if any fields are wrong
     */
    public OrderP checkOrder() throws Exception {
        List<OrderP> orderPList;
        orderPList = orderPBLL.findAllOrders();
        int id, clientId, productId, quantity;
        try {
            id = Integer.parseInt(view.getOrderId());
            clientId = Integer.parseInt(view.getClientOrder());
            productId = Integer.parseInt(view.getProductOrder());
            quantity = Integer.parseInt(view.getQuantity());
        } catch (NumberFormatException e) {
            throw new Exception("All ids and quantity must be numbers!");
        }
        if (id <= 0 || clientId <= 0 || productId <= 0 || quantity <= 0)
            throw new Exception("Numbers must be positive!");
        for (OrderP o : orderPList)
            if (o.getId() == id)
                throw new Exception("This id already exists!");
        String clientName = null, productName;
        List<Client> clientList;
        clientList = clientBLL.findAllClients();
        boolean k = false;
        for (Client c: clientList)
            if (c.getId() == clientId) {
                k = true;
                clientName = c.getName();
                break;
            }
        if (!k)
            throw new Exception("The client id doesn't exist");
        List<Product> productList;
        productList = productBLL.findAllProducts();
        for (Product p: productList)
            if (p.getId() == productId){
                if (p.getStock() < quantity)
                    throw new Exception("Not enough stock!");
                productName = p.getName();
                p.setStock(p.getStock() - quantity);
                String[] newField = {"", "", String.valueOf(p.getStock())};
                productBLL.updateProduct(newField, p.getId());
                billBLL.insertBill(new Bill(id, clientName, productName,quantity, (p.getPrice() * quantity)));
                return new OrderP(id, clientName, productName, quantity);
            }
        throw new Exception("The product id doesn't exist");
    }

}
