package com.ali.ssbdeliveryboy.modelclasses;

public class modelorders {
    String _id;
    orderId orderId;

    public modelorders(String _id, com.ali.ssbdeliveryboy.modelclasses.orderId orderId) {
        this._id = _id;
        this.orderId = orderId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public com.ali.ssbdeliveryboy.modelclasses.orderId getOrderId() {
        return orderId;
    }

    public void setOrderId(com.ali.ssbdeliveryboy.modelclasses.orderId orderId) {
        this.orderId = orderId;
    }
}
