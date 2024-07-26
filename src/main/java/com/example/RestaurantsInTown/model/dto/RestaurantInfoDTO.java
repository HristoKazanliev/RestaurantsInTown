package com.example.RestaurantsInTown.model.dto;

import com.example.RestaurantsInTown.model.entity.Restaurant;

public class RestaurantInfoDTO {
    private long id;
    private String name;
    private String location;
    private String imageUrl;

    public RestaurantInfoDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.imageUrl = restaurant.getImageUrl();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
