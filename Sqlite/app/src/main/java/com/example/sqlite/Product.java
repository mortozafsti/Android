package com.example.sqlite;

public class Product {

    private int id;
    private String name;
    private int qty;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public Product(int id, String name, int qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                '}';
    }
}
