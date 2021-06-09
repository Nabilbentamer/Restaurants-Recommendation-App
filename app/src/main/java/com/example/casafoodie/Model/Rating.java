package com.example.casafoodie.Model;

public class Rating {
    String content,rating_value,restau_id,user_phone;

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating_value(String rating_value) {
        this.rating_value = rating_value;
    }

    public void setRestau_id(String restau_id) {
        this.restau_id = restau_id;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getContent() {
        return content;
    }

    public String getRating_value() {
        return rating_value;
    }

    public String getRestau_id() {
        return restau_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public Rating(String content, String rating_value, String restau_id, String user_phone) {
        this.content = content;
        this.rating_value = rating_value;
        this.restau_id = restau_id;
        this.user_phone = user_phone;
    }

    public Rating() {
    }
}

