package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

import java.util.List;

/**
 * Class which provide the operations on Client
 */
public class ClientBLL {

    private ClientDAO clientDAO;

    /**
     * The field to access Client DataBase
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * The method to get all Clients from DataBase
     * @return List of clients from the DataBase
     */
    public List<Client> findAllClients() {
        return this.clientDAO.findAll();
    }

    /**
     * The method to insert a Client in DataBase
     * @param c the Client we want to insert
     */
    public void insertClient(Client c) {
        String[] values = {String.valueOf(c.getId()), c.getName(), String.valueOf(c.getAge()), c.getAdress()};
        this.clientDAO.insert(values);
    }

    /**
     * The method to delete a Client from DataBase
     * @param id the Client we want to delete
     */
    public void deleteClient(int id) {
        this.clientDAO.delete(id);
    }

    /**
     * The Method to update a Client from DataBase
     * @param newFields the new fields we want to insert
     * @param id the Client we want to update
     */
    public void updateClient(String[] newFields, int id) {
        int fieldsNumber = 0;
        for (String s : newFields) {
            if (s.length() != 0)
                fieldsNumber++;
        }
        String[] fields = new String[fieldsNumber];
        int i = 0;
        if (newFields[i].length() != 0) {
            fields[i] = "name";
            i++;
        } else {
            newFields[i] = newFields[i + 1];
            newFields[i + 1] = newFields[i + 2];
        }
        if (newFields[i].length() != 0) {
            fields[i] = "age";
            i++;
        } else newFields[i] = newFields[i + 1];
        if (newFields[i].length() != 0) {
            fields[i] = "adress";
            i++;
        }

        if (i != 0)
            this.clientDAO.update(fields, newFields, id);
    }

}
