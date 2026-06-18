package com.example.recyclerview_demo;

public class Product {

    private int productId;
    private String productName;
    private int productPrice;

    public Product(){

        this.productId=100;
        this.productName="Product1";
        this.productPrice=3456;

    }

    public Product(int pId,String pName,int pPrice){

        this.productId=pId;
        this.productName=pName;
        this.productPrice=pPrice;

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {

        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
