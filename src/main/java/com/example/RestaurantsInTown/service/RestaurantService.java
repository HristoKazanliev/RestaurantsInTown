package com.example.RestaurantsInTown.service;

import com.example.RestaurantsInTown.model.dto.RestaurantAddDTO;
import com.example.RestaurantsInTown.model.dto.RestaurantDetailsDTO;
import com.example.RestaurantsInTown.model.entity.Category;
import com.example.RestaurantsInTown.model.entity.Restaurant;
import com.example.RestaurantsInTown.model.entity.UserEntity;
import com.example.RestaurantsInTown.model.enums.CategoryEnum;
import com.example.RestaurantsInTown.repository.CategoryRepository;
import com.example.RestaurantsInTown.repository.RestaurantRepository;
import com.example.RestaurantsInTown.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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

    public Map<CategoryEnum, List<Restaurant>> getAllByCategory() {
        Map<CategoryEnum, List<Restaurant>> map = new HashMap<>();
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            List<Restaurant> restaurantsByCategory = restaurantRepository.findByCategory(category);

            map.put(category.getName(), restaurantsByCategory);
        }

        return map;
    }

    public RestaurantDetailsDTO getRestaurantDetails(Long id) {
        return restaurantRepository
                .findById(id)
                .map(r -> modelMapper.map(r, RestaurantDetailsDTO.class))
                .orElseThrow();
    }
}
