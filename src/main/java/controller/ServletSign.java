package controller;

import model.Customer;
import model.Seller;
import service.admin.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Servlet", urlPatterns = "/welcome")
public class ServletSign extends HttpServlet {
    AdminService adminService = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action1 = request.getParameter("action1");
        String action2 = request.getParameter("action2");
        if (action1 == null) {
            action1 = "";
        }
        if (action2 == null) {
            action2 = "";
        }
        try {
            switch (action1) {
                case "sell":
                    if (action2.equals("sign-up")) {
                        addSeller(request, response);
                        break;
                    } else {
                        signInSeller(request, response);
                        break;
                    }
                case "shopping":
                    if (action2.equals("sign-up")) {
                        addCustomer(request, response);
                        break;
                    } else {
                        signInCustomer(request, response);
                        break;
                    }
                default:
                    welcome(request, response);

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action1 = request.getParameter("action1");
        String action2 = request.getParameter("action2");

        if (action1 == null) {
            action1 = "";
        }
        if (action2 == null) {
            action2 = "";
        }

        switch (action1) {
            case "sell":
                if (action2.equals("sign-up")) {
                    comeSignUp(request, response);
                    break;
                } else {
                    comeSignIn(request, response);
                    break;
                }
            case "shopping":
                if (action2.equals("sign-up")) {
                    comeSignUp(request, response);
                    break;
                } else {
                    comeSignIn(request, response);
                    break;
                }
            default:
                welcome(request, response);
        }
    }

    private void welcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/welcome/welcome.jsp");
        dispatcher.forward(request, response);
    }

    private void comeSignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/sign/sign_in.jsp");
        dispatcher.forward(request, response);
    }

    private void comeSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/sign/sign_up.jsp");
        dispatcher.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("psw-repeat");
        Customer customer = new Customer(name, address, phoneNumber, username, password);
        adminService.addCustomer(customer);
        request.setAttribute("customer", customer);
        ServletShopping servletShopping = new ServletShopping();
        servletShopping.doGet(request, response);

    }

    private void addSeller(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("psw-repeat");
        Seller seller = new Seller(name, address, phoneNumber, username, password);
        adminService.addSeller(seller);
        request.setAttribute("seller", seller);
        ServletSell servletSell = new ServletSell();
        servletSell.doGet(request, response);
    }

    protected void signInCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = null;
        try {
            customer = adminService.signInCustomer(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (customer == null) {
            response.sendRedirect("/welcome?action1=shopping");
        } else {
            request.setAttribute("customer", customer);
            ServletShopping servletShopping = new ServletShopping();
            servletShopping.doGet(request, response);
        }
    }

    protected void signInSeller(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Seller seller = null;
        try {
            seller = adminService.signInSeller(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (seller == null) {
            doGet(request, response);
        } else {
            request.setAttribute("seller", seller);
            ServletSell servletSell = new ServletSell();
            servletSell.doGet(request, response);
        }
    }
}
