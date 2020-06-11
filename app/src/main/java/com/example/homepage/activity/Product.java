package com.example.homepage.activity;

public class Product {
    private int prodId;
    private String prodName;
    private int prodPrice;
    private String prodImg;
    private String prodDesc;

    public Product(int id, String name, int price, String img, String desc) {
        setProdId(id);
        setProdName(name);
        setProdPrice(price);
        setProdImg(img);
        setProdDesc(desc);
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }
}
