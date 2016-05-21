package com.example.rak.kappa;

/**
 * Created by RAK on 29-Mar-16.
 */
public class Item {
    private String name;
    private int price;
    private int kal;
    public Item(String name, int price, int kal)
    {
        this.name=name;
        this.price=price;
        this.kal=kal;
    }
    public String getname()
    {
        return this.name;
    }
    public int getprice()
    {
        return this.price;
    }
    public int getkal()
    {
        return this.kal;
    }
}
