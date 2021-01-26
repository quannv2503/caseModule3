package model;

import java.sql.Timestamp;
import java.util.Date;

public class Evaluate {
    private int id;
    private Customer customer;
    private Product product;
    private String content;
    private java.sql.Timestamp date;

    public Evaluate(int id, Product product, String content, Timestamp date) {
        this.id = id;
        this.product = product;
        this.content = content;
        this.date = date;
    }

    public Evaluate(int id, Customer customer, Product product, String content, Timestamp date) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}