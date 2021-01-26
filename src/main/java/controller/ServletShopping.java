package controller;

import model.Customer;
import model.Evaluate;
import model.Product;
import model.extend.HistoryBuy;
import service.shopping.ShoppingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletShopping", urlPatterns = "/shopping")
public class ServletShopping extends HttpServlet {
    ShoppingService shoppingService = new ShoppingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "next2":
                    next2(request, response);
                    break;
                case "order":
                    order(request, response);
                    break;
                case "comment":
                    comment(request, response);
                    break;
                case "logOut":
                    logOut(request, response);
                    break;
                case "search":
                    search(request, response);
                    break;
                case "updateCustomerOrder":
                    editCustomerOrder(request, response);
                    break;
                case "updateCustomer":
                    editCustomer(request, response);
                    break;
                case "history":
                    historyByProduct(request, response);
                    break;
                default:
                    selectAll(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "buy":
                    buy(request, response);
                    break;
                case "detail":
                    detail(request, response);
                    break;
                case "user":
                    userCustomer(request, response);
                    break;
                default:
                    selectAll(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {


    }

    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            if (shoppingService.getCount() == 0) {
                Customer customer = (Customer) request.getAttribute("customer");
                shoppingService.setCustomer(customer);
                shoppingService.setCount(1);
            }
            List<Product> products = shoppingService.selectAllProduct();
            RequestDispatcher rq = request.getRequestDispatcher("view/shopping/main-shopping.jsp");
            request.setAttribute("products", products);
            request.setAttribute("customer", shoppingService.getCustomer());
            rq.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void historyByProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = shoppingService.getCustomer().getId();
        List<HistoryBuy> historyBuyList = shoppingService.historyByProduct(id);
        RequestDispatcher rq = request.getRequestDispatcher("view/shopping/historyBuy.jsp");
        request.setAttribute("historyBuyList", historyBuyList);
        rq.forward(request, response);

    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        shoppingService.setCount(0);
        response.sendRedirect("/welcome?action1=shopping");
    }

    private void comment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int product_id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        int customer_id = shoppingService.getCustomer().getId();
        shoppingService.addComment(content, product_id, customer_id);
    }


    public void next2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int quantityBuy = Integer.parseInt(request.getParameter("quantityBuy"));
        int idProduct = Integer.parseInt(request.getParameter("id"));
        int quantity_product = Integer.parseInt(request.getParameter("quantity-product"));
        String a = "next";
        if (quantityBuy > 0 && (quantityBuy <= quantity_product)) {
            Customer customer = shoppingService.getCustomer();

            try {
                Product product = shoppingService.findProduct(idProduct);


                RequestDispatcher rq = request.getRequestDispatcher("view/shopping/buy.jsp");
                request.setAttribute("product", product);
                request.setAttribute("quantityBuy", quantityBuy);
                request.setAttribute("a", a);
                request.setAttribute("customer", customer);
                rq.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buy(request, response);
        }
    }

    public void buy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        try {
            Product product = shoppingService.findProduct(idProduct);
            RequestDispatcher rq = request.getRequestDispatcher("view/shopping/buy.jsp");
            request.setAttribute("product", product);
            rq.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        int idProduct = Integer.parseInt(request.getParameter("id"));
        try {
            List<Evaluate> evaluates = shoppingService.selectAllEvaluate(idProduct);
            Product product = shoppingService.findProduct(idProduct);
            RequestDispatcher rq = request.getRequestDispatcher("view/shopping/detail.jsp");
            request.setAttribute("evaluates", evaluates);
            request.setAttribute("product", product);
            rq.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void order(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int quantityBuy = Integer.parseInt(request.getParameter("quantityBuy"));
        int product_id = Integer.parseInt(request.getParameter("id"));
        int quantity_product = Integer.parseInt(request.getParameter("quantity-product"));
        int customer_id = Integer.parseInt(request.getParameter("id-customer"));
        shoppingService.addOrder(quantityBuy, product_id, customer_id);
        shoppingService.updateProductOrder(quantityBuy, product_id);
        editCustomerOrder(request, response);
        response.sendRedirect("/shopping");

    }

    public void editCustomerOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id-customer"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String avatar = request.getParameter("avatar");
        Customer customer = new Customer(id, name, address, phoneNumber,avatar);
        shoppingService.editCustomerOrder(customer);
        shoppingService.setCustomer(shoppingService.findCustomer(id));
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id-customer"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String avatar = request.getParameter("avatar");
        Customer customer = new Customer(id, name, address, phoneNumber, avatar);
        shoppingService.editCustomerOrder(customer);
        shoppingService.setCustomer(shoppingService.findCustomer(id));
        response.sendRedirect("/shopping?action=user");
    }


    private void userCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/shopping/user.jsp");
        Customer customer = shoppingService.getCustomer();
        request.setAttribute("customer", customer);
        dispatcher.forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            String name = request.getParameter("search");
            List<Product> products = shoppingService.seachByName(name);
            RequestDispatcher rq = request.getRequestDispatcher("view/shopping/main-shopping.jsp");
            request.setAttribute("products", products);
            request.setAttribute("customer", shoppingService.getCustomer());
            rq.forward(request, response);
        } catch (
                ServletException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
