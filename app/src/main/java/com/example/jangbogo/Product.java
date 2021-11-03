package com.example.jangbogo;

public class Product {
    private Integer id;
    private String name;
    private Category category;

    public Product(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public void setId(Integer id) {this.id = id;}

    public Integer getId() {return id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public void setCategory(Category category) {this.category = category;}

    public Category getCategory() {return category;}
}
