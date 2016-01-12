package com.sayor.org.simplegrocerylist.models;

public class Grocery {
    public String name;
    public String desc;
    public int qty;
    public boolean done;

    public boolean isDone() {
        return done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }

    public Grocery(String name, String desc, int qty, boolean done){
        this.name = name;
        this.desc = desc;
        this.qty = qty;
        this.done = done;
    }
}
