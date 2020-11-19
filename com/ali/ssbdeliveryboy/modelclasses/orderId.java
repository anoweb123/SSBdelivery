package com.ali.ssbdeliveryboy.modelclasses;

public class orderId {
    String status,orderTime,_id,customerId,name,address,grandTotal,paymentMethod,paymentStatus,longnitude,latitude,deliveryTime,cell;

    public orderId(String status, String orderTime, String _id, String customerId, String name, String address, String grandTotal, String paymentMethod, String paymentStatus, String longnitude, String latitude) {
        this.status = status;
        this.orderTime = orderTime;
        this._id = _id;
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.cell = cell;
        this.grandTotal = grandTotal;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.longnitude = longnitude;
        this.deliveryTime = deliveryTime;
        this.latitude = latitude;
    }

    public orderId() {
    }



    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getLongnitude() {
        return longnitude;
    }

    public void setLongnitude(String longnitude) {
        this.longnitude = longnitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
