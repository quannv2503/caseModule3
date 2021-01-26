package service.admin;

import model.Customer;
import model.Seller;
import service.connect.Connect;

import java.sql.*;

public class AdminService implements IAdminService {
    Connect connect = new Connect();

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        boolean check = false;
        String insertCustomer = "INSERT INTO customer(name ,address,phoneNumber,username,password) VALUES(?, ?, ?,?,?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertCustomer)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getUsername());
            preparedStatement.setString(5, customer.getPassword());
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public Customer signInCustomer(String username, String password) throws SQLException {
        String checkSignInCustomer = "{call check_sign_in_customer(?,?)}";
        try {
            Connection connection = connect.getConnection();
            CallableStatement callableStatement = connection.prepareCall(checkSignInCustomer);
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String avatar=resultSet.getString("avatar");
                Customer customer = new Customer(id, name, address, phoneNumber, username1, password1,avatar);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addSeller(Seller seller) throws SQLException {
        boolean check = false;
        String insertSeller = "INSERT INTO seller(name ,address,phoneNumber,username,password) VALUES(?, ?, ?,?,?);";
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSeller)) {
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getAddress());
            preparedStatement.setString(3, seller.getPhoneNumber());
            preparedStatement.setString(4, seller.getUsername());
            preparedStatement.setString(5, seller.getPassword());
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }


    @Override
    public Seller signInSeller(String username, String password) throws SQLException {
        String checkSignInSeller = "{call check_sign_in_seller(?,?)}";
        try {
            Connection connection = connect.getConnection();
            CallableStatement callableStatement = connection.prepareCall(checkSignInSeller);
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Seller seller = new Seller(id, name, address, phoneNumber, username1, password1);
                return seller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
}
