package com.example.roller;

import com.example.roller.domain.Product;

import java.util.HashMap;

public class Ordere_info {
    private HashMap<Product , Integer> order_item;
    private String order_time;
    private String order_path;

    public Ordere_info(HashMap<Product, Integer> order_item, String order_time, String order_path) {
        this.order_item = order_item;
        this.order_time = order_time;
        this.order_path = order_path;
    }

    public HashMap<Product, Integer> getOrder_item() {
        return order_item;
    }

    public void setOrder_item(HashMap<Product, Integer> order_item) {
        this.order_item = order_item;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_path() {
        return order_path;
    }

    public void setOrder_path(String order_path) {
        this.order_path = order_path;
    }
}
