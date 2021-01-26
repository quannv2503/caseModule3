package controller;

import model.Customer;
import model.Evaluate;
import model.Product;
import model.Seller;
import model.extend.General;
import model.extend.HistoryBuy;
import service.sell.SellService;
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

@WebServlet(name = "ServletSell", urlPatterns = "/sell")
public class ServletSell extends HttpServlet {
    SellService sellService = new SellService();
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
                case "add":
                    addProduct(request, response);
                    break;
                case "edit":
                    editProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "logOut":
                    logOut(request, response);
                    break;
                case "general":
                    general(request, response);
                    break;
                case "comment":
                    comment(request, response);
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
                case "search":
                    break;
                case "logOut":
                    logOut(request, response);
                    break;
                case "rep":
                    rep(request, response);
                    break;
                default:
                    selectAll(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void comment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int product_id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        int customer_id = 0;
        shoppingService.addComment(content, product_id, customer_id);
    }


    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            if (sellService.getCount() == 0) {
                Seller seller = (Seller) request.getAttribute("seller");
                sellService.setSeller(seller);
                sellService.setCount(1);
            }
            int id = sellService.getSeller().getId();
            List<Product> products = sellService.selectAll(id);
            RequestDispatcher rq = request.getRequestDispatcher("view/seller/main-seller.jsp");
            request.setAttribute("products", products);
            rq.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rep(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        try {
            List<Evaluate> evaluates = shoppingService.selectAllEvaluate(idProduct);
            Product product = shoppingService.findProduct(idProduct);
            RequestDispatcher rq = request.getRequestDispatcher("view/seller/listComment.jsp");
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

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        sellService.setCount(0);
        response.sendRedirect("/welcome?action1=sell");
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int sellerId = sellService.getSeller().getId();
        Seller seller = sellService.findSeller(sellerId);
        Product product = new Product(name, price, quantity, discount, type, description, image, seller);
        sellService.addProduct(product);
        response.sendRedirect("/sell");
    }

    public void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        Product product = new Product(id, name, price, quantity, discount, type, description, image);
        sellService.editProduct(product);
        response.sendRedirect("/sell");
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        sellService.deleteProduct(id);
        response.sendRedirect("/sell");
    }

    private void general(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = sellService.getSeller().getId();
        List<General> generalList = sellService.general(id);
        RequestDispatcher rq = request.getRequestDispatcher("view/seller/genneral.jsp");
        request.setAttribute("generalList", generalList);
        rq.forward(request, response);

    }

}

