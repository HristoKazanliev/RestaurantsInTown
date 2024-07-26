package com.example.RestaurantsInTown.model.dto;

import com.example.RestaurantsInTown.model.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestaurantAddDTO {
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @NotBlank
    private String imageUrl;

    @NotBlank
    @Size(min = 3, max = 40)
    private String location;

    @NotNull
    private CategoryEnum category;

    public RestaurantAddDTO() {

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

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
