package service.sell;

import model.Customer;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ISellService {
    List<Product> selectAll(int id) throws SQLException;
    boolean addProduct(Product product) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
    boolean editProduct(Product product) throws SQLException;
}
