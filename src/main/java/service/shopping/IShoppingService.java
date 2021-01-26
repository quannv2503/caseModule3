package service.shopping;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IShoppingService {
    List<Product> selectAllProduct() throws SQLException;

}
