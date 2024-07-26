package com.example.RestaurantsInTown.model.dto;

import com.example.RestaurantsInTown.model.enums.CategoryEnum;

public class RestaurantDetailsDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private String location;
    private String description;

    public RestaurantDetailsDTO() {
    }

    public RestaurantDetailsDTO(Long id, String name, String imageUrl,String location, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
