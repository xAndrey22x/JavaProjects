package BusinessLogic;

import DataAccess.OrderPDAO;
import Model.OrderP;

import java.util.List;
/**
 * Class which provide the operations on Order
 */
public class OrderPBLL {
    private OrderPDAO orderPDAO;
    /**
     * The field to access order DataBase
     */
    public OrderPBLL() {
        orderPDAO = new OrderPDAO();
    }

    /**
     * The method to get all Orders from DataBase
     * @return List of orders from the DataBase
     */
    public List<OrderP> findAllOrders() {
        return orderPDAO.findAll();
    }

    /**
     * The method to insert an Order in DataBase
     * @param o order we want to insert
     */
    public void insertOrder(OrderP o) {
        String[] values = {String.valueOf(o.getId()), o.getClientName(), o.getProductName(), String.valueOf(o.getQuantity())};
        this.orderPDAO.insert(values);
    }

    /**
     * The method to delete an Order from DataBase
     * @param id the Order we want to delete
     */
    public void deleteOrder(int id) {
        this.orderPDAO.delete(id);
    }
}
