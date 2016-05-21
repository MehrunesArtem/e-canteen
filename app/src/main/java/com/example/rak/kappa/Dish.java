package com.example.rak.kappa;

import java.util.ArrayList;

public class Dish{
    private long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int time;

    public Dish(Long id, String name, double price, String description, String category, int time) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.time=time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTime() {
        return time;
    }




    public int compare(Dish a, Dish b) {
       int res = String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName());
        if (res==0)
        {
            res = a.getName().compareTo(b.getName());
        }


        return res;
    }

}
