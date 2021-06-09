package com.example.casafoodie.Model;

public class Restaurants {

    String name, price,description,image,good_for;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGood_for(String good_for) {
        this.good_for = good_for;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getGood_for() {
        return good_for;
    }

    public Restaurants(String name, String price, String description, String image, String good_for) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.good_for = good_for;
    }

    public Restaurants() {
    }
}

