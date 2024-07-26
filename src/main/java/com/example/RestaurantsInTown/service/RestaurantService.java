package com.example.RestaurantsInTown.service;

import com.example.RestaurantsInTown.model.dto.RestaurantAddDTO;
import com.example.RestaurantsInTown.model.entity.Category;
import com.example.RestaurantsInTown.model.entity.Restaurant;
import com.example.RestaurantsInTown.model.entity.UserEntity;
import com.example.RestaurantsInTown.repository.CategoryRepository;
import com.example.RestaurantsInTown.repository.RestaurantRepository;
import com.example.RestaurantsInTown.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, UserRepository userRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public boolean addRestaurant(RestaurantAddDTO restaurantAddDTO, UserDetails userDetails) {
        Optional<Category> categoryByName = categoryRepository.findByName(restaurantAddDTO.getCategory());
        if (categoryByName.isEmpty()) {
            return false;
        }

        Restaurant restaurant = modelMapper.map(restaurantAddDTO, Restaurant.class);
        UserEntity user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        restaurant.setCategory(categoryByName.get());
        restaurant.setAddedBy(user);

        restaurantRepository.save(restaurant);

        return true;
    }
}
