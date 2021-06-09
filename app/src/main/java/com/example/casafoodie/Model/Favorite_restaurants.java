package com.example.casafoodie.Model;

public class Favorite_restaurants {

    String restau_id,user_phone;

    public Favorite_restaurants(String restau_id, String user_phone) {
        this.restau_id = restau_id;
        this.user_phone = user_phone;
    }

    public Favorite_restaurants() {
    }

    public void setRestau_id(String restau_id) {
        this.restau_id = restau_id;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getRestau_id() {
        return restau_id;
    }

    public String getUser_phone() {
        return user_phone;
    }
}
