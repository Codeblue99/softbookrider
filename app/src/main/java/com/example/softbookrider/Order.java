package com.example.softbookrider;

public class Order {
    String order_id,user_id,order_amount,delivery_charge,total_amount,order_note,bill_date,user_name,user_phone;

    public Order(String order_id, String user_id, String order_amount, String delivery_charge, String total_amount, String order_note, String bill_date, String user_name, String user_phone) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_amount = order_amount;
        this.delivery_charge = delivery_charge;
        this.total_amount = total_amount;
        this.order_note = order_note;
        this.bill_date = bill_date;
        this.user_name = user_name;
        this.user_phone = user_phone;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
