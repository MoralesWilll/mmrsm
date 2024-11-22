package org.example.entities;

import java.sql.Date;

public class Client {

    private Integer client_id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Client(Integer client_id, String name, String email, String phone, String address) {
        this.client_id = client_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "Name= " + name +
                ", Email= " + email +
                ", Phone= " + phone +
                ", Address= " + address +
                '\n';
    }

}
