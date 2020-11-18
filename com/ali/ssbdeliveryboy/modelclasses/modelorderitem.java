package com.ali.ssbdeliveryboy.modelclasses;

public class modelorderitem {
String image,price,size,color,productName,quantity;

    public modelorderitem(String image, String price, String size, String color, String productName, String quantity) {
        this.image = image;
        this.price = price;
        this.size = size;
        this.color = color;
        this.productName = productName;
        this.quantity = quantity;
    }

    public modelorderitem() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
