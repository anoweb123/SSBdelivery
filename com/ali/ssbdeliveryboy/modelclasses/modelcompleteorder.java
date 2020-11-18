package com.ali.ssbdeliveryboy.modelclasses;

public class modelcompleteorder {
    String _id;
    orderId orderId;

    public modelcompleteorder(String _id, orderId orderId) {
        this._id = _id;
        this.orderId = orderId;
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
