package DataAccess;

import Model.Bill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import Connection.ConnectionFactory;
/**
 * Bill DAO class
 */
public class BillDAO extends AbstractDAO<Bill> {

    /**
     * Custom select querry for Bill cause of the record class
     * @return the querry
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("bill");
        return sb.toString();
    }

    /**
     * Custom findAll method for Bill cause of the record class
     * @return list of bills
     */
    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Bill> list = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String clientName = resultSet.getString("clientName");
                String productName = resultSet.getString("productName");
                int stock = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");
                list.add(new Bill(id, clientName, productName, stock, price));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Bill" + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
