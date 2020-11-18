package com.ali.ssbdeliveryboy.modelclasses;

public class modellogin {

    String name,status,shopId,cell,email,password,_id,address,image;

    public modellogin(String name, String status, String shopId, String cell, String email, String password, String _id, String address, String image) {
        this.name = name;
        this.status = status;
        this.shopId = shopId;
        this.cell = cell;
        this.email = email;
        this.password = password;
        this._id = _id;
        this.address = address;
        this.image = image;
    }
    public modellogin() {
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
