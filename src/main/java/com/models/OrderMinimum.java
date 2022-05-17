package com.models;

import java.util.ArrayList;
import java.util.Set;

public class OrderMinimum {
    private long customerid;

    private String Comments;

    private ArrayList<OrderItem> items = new ArrayList<>();

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
