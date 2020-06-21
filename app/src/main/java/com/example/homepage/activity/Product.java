package com.example.homepage.activity;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Serializable {
    private int prodID;
    private String prodName;
    private int prodPrice;
    private String prodImg;
    private String prodDes;
    private int prodCateID;

    public Product(int prodID, String prodName, int prodPrice, String prodImg, String prodDes, int prodCateID) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodImg = prodImg;
        this.prodDes = prodDes;
        this.prodCateID = prodCateID;
    }

    public String getProdDes() {
        return prodDes;
    }

    public void setProdDes(String prodDes) {
        this.prodDes = prodDes;
    }

    public int getProdCateID() {
        return prodCateID;
    }

    public void setProdCateID(int prodCateID) {
        this.prodCateID = prodCateID;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public static Comparator<Product> priceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product prod1, Product prod2) {
            if (prod1.getProdPrice() < prod2.getProdPrice())
                return -1;
            else if (prod1.getProdPrice() == prod2.getProdPrice())
                return 0;
            else
                return 1;
        }
    };

    public static Comparator<Product> nameComparator = new Comparator<Product>() {
        @Override
        public int compare(Product prod1, Product prod2) {
            return prod1.getProdName().compareTo(prod2.getProdName());
        }
    };

    public String getProdName() {
        return prodName;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }
}
