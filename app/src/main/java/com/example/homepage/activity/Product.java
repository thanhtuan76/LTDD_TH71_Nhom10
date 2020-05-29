package com.example.homepage.activity;

public class Product {
    private String prodName;
    private String prodPrice;
    private int prodImg;

    public Product(){
    }

    public Product(String name, String price, int img) {
        setProdName(name);
        setProdPrice(price);
        setProdImg(img);
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getProdImg() {
        return prodImg;
    }

    public void setProdImg(int prodImg) {
        this.prodImg = prodImg;
    }
}
