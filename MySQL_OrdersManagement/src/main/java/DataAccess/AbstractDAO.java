package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * Abstract class to use for all DataBases
 */
public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Get the type which is used.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Create SELECT ALL querry
     * @return the querry
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Create INSERT INTO querry
     * @param values the new fields which we want to insert
     * @return the querry
     */
    private String createInsertQuerry(String[] values) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES(");
        for (int i = 0; i < values.length - 1; i++) {
            sb.append('\'' + values[i] + '\'');
            sb.append(",");
        }
        sb.append('\'' + values[values.length - 1] + '\'');
        sb.append(")");
        return sb.toString();
    }

    /**
     * Create DELETE FROM querry
     * @param field provide the id which we want to delete
     * @return the querry
     */
    private String createRemoveQuerry(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Create UPDATE querry
     * @param fields the fields we want to update
     * @param id the id we want to update
     * @return the querry
     */
    private String createUpdateQuerry(String[] fields, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET");
        for (int i = 0; i < fields.length - 1; i++) {
            sb.append(" " + fields[i] + " =?, ");
        }
        sb.append(" " + fields[fields.length - 1] + " =?");
        sb.append(" WHERE " + id + " =?");
        return sb.toString();
    }

    /**
     * The method which will call the update querry and make the connection with the DataBase
     * @param fields fields we want to update
     * @param fieldsValue values of fields
     * @param id the id we want to update
     */
    public void update(String[] fields, String[] fieldsValue, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuerry(fields, "id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i;
            for (i = 0; i < fields.length; i++)
                statement.setString((i + 1), fieldsValue[i]);
            statement.setInt((i + 1), id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * The method which will call the delete querry and make the connection with the DataBase
     * @param id the id we want to delete
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createRemoveQuerry("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * The method which will call the insert querry and make the connection with the DataBase
     * @param values values we wanna insert
     */
    public void insert(String values[]) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuerry(values);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * The method which will call the select all querry and make the connection with the DataBase
     * @return all values from the DataBase
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method to create the list of objects we extract from DataBase using 'reflexion'
     * @param resultSet the resultSet from DataBase
     * @return all values from DataBase
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                 InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

}
