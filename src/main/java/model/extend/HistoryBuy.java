package model.extend;

public class HistoryBuy {
    private String name;
    private String quantityBuy;
    private String image;
    private double price;
    private int id;

    public HistoryBuy(String name, String quantityBuy, String image,double price,int id) {
        this.name = name;
        this.quantityBuy = quantityBuy;
        this.image = image;
        this.price=price;
        this.id=id;
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

    public String getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(String quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
