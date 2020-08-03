package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;
    List<LineItem> listOfLineItem;

    public Order(String name, String address, List<LineItem> listOfLineItem) {
        this.name = name;
        this.address = address;
        this.listOfLineItem = listOfLineItem;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return listOfLineItem;
    }
}
