package com.example.jangbogo.model;

import com.example.jangbogo.model.Product;

public class Stock {
    private Integer id;
    private int price;
    private int count;
    private Product product;

    public Stock(Integer id, int price, int count, Product product) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.product = product;
    }

    public void setId(Integer id) { this.id = id; }

    public Integer getId() {
        return id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
