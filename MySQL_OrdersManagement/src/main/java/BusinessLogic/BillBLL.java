package BusinessLogic;


import DataAccess.BillDAO;
import Model.Bill;

import java.util.List;

/**
 * Class which provide the operations on Bill
 */
public class BillBLL {

    private BillDAO billDAO;

    /**
     * The field to access Bill DataBase
     */
    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    /**
     * The method to get all Bills from DataBase
     * @return List of bills from the DataBase
     */
    public List<Bill> findAllBills() {
        return billDAO.findAll();
    }

    /**
     * The method to insert a Bill in DataBase
     * @param b bill we want to insert
     */
    public void insertBill(Bill b) {
        String[] values = {String.valueOf(b.id()), b.clientName(), b.productName(), String.valueOf(b.quantity()), String.valueOf(b.price())};
        this.billDAO.insert(values);
    }

    /**
     * The method to delete a Bill from DataBase
     * @param id the Bill we want to delete
     */
    public void deleteBill(int id) {
        this.billDAO.delete(id);
    }
}
