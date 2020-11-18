package com.ali.ssbdeliveryboy.modelclasses;

public class modelorders {
    String _id;
    orderId orderId;

    public modelorders(String _id, orderId orderId) {
        this._id = _id;
        this.orderId = orderId;
    }

    public modelorders() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public orderId getOrderId() {
        return orderId;
    }

    public void setOrderId(orderId orderId) {
        this.orderId = orderId;
    }
}
