package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int discount;
    private double realPrice;
    private String type;
    private String description;
    private String image;
    Seller seller;

    public Product() {
    }

    public Product(String name, double price, int quantity, int discount, String type, String description, String image, Seller seller) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.realPrice = price - (price * discount) / 100;
        this.type = type;
        this.description = description;
        this.image = image;
        this.seller = seller;
    }

    public Product(int id, String name, double price, int quantity, int discount, String type, String description, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.realPrice = price - (price * discount) / 100;
        this.type = type;
        this.description = description;
        this.image = image;
        this.id = id;
    }

    public Product(int id, String name, double price, int quantity, int discount, String type, String description, String image, Seller seller) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.realPrice = price - (price * discount) / 100;
        this.type = type;
        this.description = description;
        this.image = image;
        this.id = id;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }







    public void setDiscount(int discount) {
        this.discount = discount;
        this.realPrice = price - (price * discount) / 100;
    }





    
    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
