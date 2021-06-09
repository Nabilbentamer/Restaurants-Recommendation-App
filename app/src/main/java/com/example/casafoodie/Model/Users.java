package com.example.casafoodie.Model;

public class Users {
    String  username, phone_number,password,email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Users(String username, String phone_number, String password, String email) {
        this.username = username;
        this.phone_number = phone_number;
        this.password = password;
        this.email = email;
    }

    public Users() {
    }
}
