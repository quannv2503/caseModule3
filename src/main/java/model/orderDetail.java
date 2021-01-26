package model;

public class orderDetail {
    private int id;
    private Product product;
    private Customer customer;
    private int quantityBuy;

    public orderDetail() {
    }

    public orderDetail(int id, Product product, int quantityBuy) {
        this.id = id;
        this.product = product;
        this.quantityBuy = quantityBuy;
    }

    public orderDetail(Product product, Customer customer, int quantityBuy) {
        this.customer = customer;
        this.product = product;
        this.quantityBuy = quantityBuy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
