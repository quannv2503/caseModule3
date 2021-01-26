package model.extend;

public class General {
    private int id;
    private String name;
    private int sumQuantityBuy;
    private double price;
    private String image;

    public General(int id, String name, int sumQuantityBuy, double price,String image) {
        this.id = id;
        this.name = name;
        this.sumQuantityBuy = sumQuantityBuy;
        this.price = price;
        this.image=image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumQuantityBuy() {
        return sumQuantityBuy;
    }

    public void setSumQuantityBuy(int sumQuantityBuy) {
        this.sumQuantityBuy = sumQuantityBuy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
