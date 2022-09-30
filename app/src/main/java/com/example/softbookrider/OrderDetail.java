package com.example.softbookrider;

public class OrderDetail {
    String el_address1,order_qty,product_name,bookstore_name,bookstore_address,bookstore_latitude,bookstore_longitude,user_latitude,user_longitude;

    public OrderDetail(String el_address1, String order_qty, String product_name, String bookstore_name, String bookstore_address, String bookstore_latitude, String bookstore_longitude, String user_latitude, String user_longitude) {
        this.el_address1 = el_address1;
        this.order_qty = order_qty;
        this.product_name = product_name;
        this.bookstore_name = bookstore_name;
        this.bookstore_address = bookstore_address;
        this.bookstore_latitude = bookstore_latitude;
        this.bookstore_longitude = bookstore_longitude;
        this.user_latitude = user_latitude;
        this.user_longitude = user_longitude;
    }

    public String getUser_latitude() {
        return user_latitude;
    }

    public void setUser_latitude(String user_latitude) {
        this.user_latitude = user_latitude;
    }

    public String getUser_longitude() {
        return user_longitude;
    }

    public void setUser_longitude(String user_longitude) {
        this.user_longitude = user_longitude;
    }

    public OrderDetail(String el_address1, String order_qty, String product_name, String bookstore_name, String bookstore_address, String bookstore_latitude, String bookstore_longitude) {
        this.el_address1 = el_address1;
        this.order_qty = order_qty;
        this.product_name = product_name;
        this.bookstore_name = bookstore_name;
        this.bookstore_address = bookstore_address;
        this.bookstore_latitude = bookstore_latitude;
        this.bookstore_longitude = bookstore_longitude;
    }

    public OrderDetail(String el_address1, String order_qty, String product_name, String bookstore_name, String bookstore_address) {
        this.el_address1 = el_address1;
        this.order_qty = order_qty;
        this.product_name = product_name;
        this.bookstore_name = bookstore_name;
        this.bookstore_address = bookstore_address;
    }

    public String getBookstore_latitude() {
        return bookstore_latitude;
    }

    public void setBookstore_latitude(String bookstore_latitude) {
        this.bookstore_latitude = bookstore_latitude;
    }

    public String getBookstore_longitude() {
        return bookstore_longitude;
    }

    public void setBookstore_longitude(String bookstore_longitude) {
        this.bookstore_longitude = bookstore_longitude;
    }

    public String getEl_address1() {
        return el_address1;
    }

    public void setEl_address1(String el_address1) {
        this.el_address1 = el_address1;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(String order_qty) {
        this.order_qty = order_qty;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBookstore_name() {
        return bookstore_name;
    }

    public void setBookstore_name(String bookstore_name) {
        this.bookstore_name = bookstore_name;
    }

    public String getBookstore_address() {
        return bookstore_address;
    }

    public void setBookstore_address(String bookstore_address) {
        this.bookstore_address = bookstore_address;
    }
}
