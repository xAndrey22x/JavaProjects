package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

import java.util.List;
/**
 * Class which provide the operations on Product
 */
public class ProductBLL {

    private ProductDAO productDAO;
    /**
     * The field to access Product DataBase
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * The method to get all Products from DataBase
     * @return List of products from the DataBase
     */
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    /**
     * The method to insert a Product in DataBase
     * @param p the Product we want to insert
     */
    public void insertProduct(Product p) {
        String[] values = {String.valueOf(p.getId()), p.getName(), String.valueOf(p.getPrice()), String.valueOf(p.getStock())};
        this.productDAO.insert(values);
    }

    /**
     * The method to delete a Product from DataBase
     * @param id the Product we want to delete
     */
    public void deleteProduct(int id) {
        this.productDAO.delete(id);
    }

    /**
     * The Method to update a Product from DataBase
     * @param newFields the new fields we want to insert
     * @param id the Product we want to update
     */
    public void updateProduct(String[] newFields, int id) {
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
            fields[i] = "price";
            i++;
        } else newFields[i] = newFields[i + 1];
        if (newFields[i].length() != 0) {
            fields[i] = "stock";
            i++;
        }

        if (i != 0)
            this.productDAO.update(fields, newFields, id);
    }

}
