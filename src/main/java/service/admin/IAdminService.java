package service.admin;

import model.Customer;
import model.Seller;

import java.sql.SQLException;

public interface IAdminService{
    boolean addCustomer(Customer customer) throws SQLException;
    Customer signInCustomer(String username,String password) throws SQLException;
    boolean addSeller(Seller seller) throws SQLException;
    Seller signInSeller(String username,String password) throws SQLException;

}
