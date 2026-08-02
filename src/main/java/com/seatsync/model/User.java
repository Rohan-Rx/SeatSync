package com.seatsync.model;

import java.sql.Timestamp;

public class User {
    private int user_id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Timestamp created_at;

    public User() {
    }
    //Constructor without userId and created_at (used while registration)
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password=password;
    }
    //Constructor with all fields (used while retriving data)

    public User(int user_id, String name, String email, String phone, String password, Timestamp created_at) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", password=" + password +
                ", created_at=" + created_at +
                '}';
    }
}
