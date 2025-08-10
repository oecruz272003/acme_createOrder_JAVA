package com.acme.model;

public class OrderRequest {
    private String productId;
    private int units;
    private double price;
    private String clientID;
    private String product_description;
    private String product_category;

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getClientID() { return clientID; }
    public void setClientID(String clientID) { this.clientID = clientID; }
    public String getProduct_description() { return product_description; }
    public void setProduct_description(String product_description) { this.product_description = product_description; }
    public String getProduct_category() { return product_category; }
    public void setProduct_category(String product_category) { this.product_category = product_category; }
}
