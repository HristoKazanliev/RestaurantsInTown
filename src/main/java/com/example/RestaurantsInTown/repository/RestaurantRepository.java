package com.example.RestaurantsInTown.repository;

import com.example.RestaurantsInTown.model.entity.Category;
import com.example.RestaurantsInTown.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCategory(Category category);
}
