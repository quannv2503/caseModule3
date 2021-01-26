package service.shopping;

import model.Customer;
import model.Evaluate;
import model.Product;
import model.Seller;
import model.extend.HistoryBuy;
import service.connect.Connect;
import service.sell.SellService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingService implements IShoppingService {
    private static Customer customer;
    private static int count = 0;
    Connect connect = new Connect();
    SellService sellService = new SellService();

    @Override
    public List<Product> selectAllProduct() throws SQLException {
        Product product = null;
        String selectAllProduct = "select * from product;";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllProduct);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int discount = rs.getInt("discount");
                double realPrice = rs.getDouble("realPrice");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String image = rs.getString("image");
                product = new Product(id, name, price, quantity, discount, type, description, image);
                productList.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

    public List<Evaluate> selectAllEvaluate(int idProduct) throws SQLException {
        Evaluate evaluate = null;
        String selectAll = "select * from evaluate where product_id=? order by date desc ;";
        List<Evaluate> evaluateList = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAll);) {
            preparedStatement.setInt(1, idProduct);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                int product_id = rs.getInt("product_id");
                int customer_id = rs.getInt("customer_id");
                Timestamp date = rs.getTimestamp("date");
                Product product1 = findProduct(product_id);
                if(customer_id==0){
                    evaluate = new Evaluate(id, product1, content, date);

                }
                else {
                    Customer customer1 = findCustomer(customer_id);

                    evaluate = new Evaluate(id, customer1, product1, content, date);
                }
                evaluateList.add(evaluate);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return evaluateList;
    }

    public boolean addOrder(int quantityBuy, int product_id, int customer_id) throws SQLException {
        boolean check = false;
        String insertOrder = "INSERT INTO orderdetail(quantityBuy,product_id,customer_id) VALUES( ?, ?,?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertOrder)) {
            preparedStatement.setInt(1, quantityBuy);
            preparedStatement.setInt(2, product_id);
            preparedStatement.setInt(3, customer_id);
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    public boolean addComment(String content, int product_id, int customer_id) throws SQLException {
        boolean check = false;
        String insert = "INSERT INTO evaluate(content,product_id,customer_id) VALUES( ?, ?,?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, product_id);
            preparedStatement.setInt(3, customer_id);
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    public Product findProduct(int id) throws SQLException {
        String check = "{call check_find_product(?)}";
        try {
            Connection connection = connect.getConnection();
            CallableStatement callableStatement = connection.prepareCall(check);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int discount = resultSet.getInt("discount");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int seller_id = resultSet.getInt("seller_id");
                Seller seller = sellService.findSeller(seller_id);
                Product product = new Product(id1, name, price, quantity, discount, type, description, image, seller);
                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer findCustomer(int id) throws SQLException {
        String check = "{call check_find_customer(?)}";
        try {
            Connection connection = connect.getConnection();
            CallableStatement callableStatement = connection.prepareCall(check);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String username = resultSet.getString("username");
                String avatar = resultSet.getString("avatar");
                Customer customer = new Customer(id1, name, address, phoneNumber, username, avatar);
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editCustomerOrder(Customer customer) throws SQLException {
        boolean rowUpdated;
        String edit = "call editCustomer(?,?,?,?,?);";
        try (Connection connection = connect.getConnection();
             CallableStatement callableStatement = connection.prepareCall(edit)) {
            callableStatement.setInt(1, customer.getId());
            callableStatement.setString(2, customer.getName());
            callableStatement.setString(3, customer.getAddress());
            callableStatement.setString(4, customer.getPhoneNumber());
            callableStatement.setString(5, customer.getAvatar());
            rowUpdated = callableStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean updateProductOrder(int quantityBuy, int produc_id) throws SQLException {
        boolean rowUpdated;
        String edit = "update product set quantity=(quantity-?) where id=?";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(edit)) {
            preparedStatement.setInt(1, quantityBuy);
            preparedStatement.setInt(2, produc_id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
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

    public List<Product> seachByName(String name) {
        Product product = null;
        String seach = "select * from product where name like concat('%',?,'%');";
        List<Product> products = new ArrayList<>();
        try (Connection connection = connect.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(seach);) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int discount = rs.getInt("discount");
                double realPrice = rs.getDouble("realPrice");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String image = rs.getString("image");
                product = new Product(id, name1, price, quantity, discount, type, description, image);
                products.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<HistoryBuy> historyByProduct(int id) {
        HistoryBuy historyBuy = null;
        String selectAll = "select p.name,o.quantityBuy,p.image,p.price,p.id\n" +
                "from customer as c,product as p,orderdetail as o\n" +
                "where c.id=o.customer_id and p.id=o.product_id and c.id=?;";
        List<HistoryBuy> List = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAll);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String quantityBuy = rs.getString("quantityBuy");
                String image = rs.getString("image");
                double price=rs.getDouble("price");
                int id1 =rs.getInt("id");
                historyBuy = new HistoryBuy(name, quantityBuy, image,price,id1);
                List.add(historyBuy);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return List;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
