package service.sell;

import model.Product;
import model.Seller;
import model.extend.General;
import model.extend.HistoryBuy;
import service.connect.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellService implements ISellService {
    Connect connect = new Connect();
    public static Seller seller;
    public static int count;

    @Override
    public List<Product> selectAll(int id) throws SQLException {
        Product product = null;
        String selectAllProduct = "select * from product where seller_id=(?);";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllProduct);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int discount = rs.getInt("discount");
                double realPrice = rs.getDouble("realPrice");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String image = rs.getString("image");
                product = new Product(id1, name, price, quantity, discount, type, description, image);
                productList.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public boolean addProduct(Product product) throws SQLException {
        boolean check = false;
        String insertProduct = "INSERT INTO product(name ,price,quantity,discount,type,description,image,realPrice,seller_id) VALUES(?, ?, ?,?,?,?, ?, ?,?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertProduct)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getDiscount());
            preparedStatement.setString(5, product.getType());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setString(7, product.getImage());
            preparedStatement.setDouble(8, product.getRealPrice());
            preparedStatement.setInt(9, product.getSeller().getId());
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    public Seller findSeller(int id) throws SQLException {
        String check = "{call check_find_seller(?)}";
        try {
            Connection connection = connect.getConnection();
            CallableStatement callableStatement = connection.prepareCall(check);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Seller seller = new Seller(id1, name, address, phoneNumber, username1, password1);
                return seller;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editProduct(Product product) throws SQLException {
        boolean rowUpdated;
        String edit = "call editProduct(?,?,?,?,?,?,?,?,?);";
        try (Connection connection = connect.getConnection();
             CallableStatement callableStatement = connection.prepareCall(edit)) {
            callableStatement.setInt(1, product.getId());
            callableStatement.setString(2, product.getName());
            callableStatement.setDouble(3, product.getPrice());
            callableStatement.setInt(4, product.getQuantity());
            callableStatement.setInt(5, product.getDiscount());
            callableStatement.setDouble(6, product.getRealPrice());
            callableStatement.setString(7, product.getType());
            callableStatement.setString(8, product.getDescription());
            callableStatement.setString(9, product.getImage());
            rowUpdated = callableStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        String delete = "delete from product where product.id=?;";
        try (Connection connection = connect.getConnection(); PreparedStatement statement = connection.prepareStatement(delete);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<General> general(int id) {
        General general = null;
        String selectAll = "select p.id,p.name,sum(o.quantityBuy) as sumQ,p.price,p.image\n" +
                "from product as p,orderdetail as o,seller as s\n" +
                "where p.id=o.product_id and s.id=p.seller_id and s.id=? group by p.id;";
        List<General> List = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAll);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                int sumQuantityBuy = rs.getInt("sumQ");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                general = new General(id1, name, sumQuantityBuy, price, image);
                List.add(general);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return List;

    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        SellService.seller = seller;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        SellService.count = count;
    }
}
