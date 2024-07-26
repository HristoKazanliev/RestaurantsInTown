package com.example.RestaurantsInTown.web;

import com.example.RestaurantsInTown.model.dto.RestaurantInfoDTO;
import com.example.RestaurantsInTown.model.entity.Restaurant;
import com.example.RestaurantsInTown.model.enums.CategoryEnum;
import com.example.RestaurantsInTown.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final RestaurantService restaurantService;

    public HomeController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String notLoggedHome() {
        return "index";
    }

    @GetMapping("/home")
    public String loggedHome(Model model) {
        Map<CategoryEnum, List<Restaurant>> allRestaurants = restaurantService.getAllByCategory();

        List<RestaurantInfoDTO> cafes = allRestaurants
                .get(CategoryEnum.CAFE)
                .stream()
                .map(RestaurantInfoDTO::new)
                .toList();

        List<RestaurantInfoDTO> pubs = allRestaurants
                .get(CategoryEnum.PUB)
                .stream()
                .map(RestaurantInfoDTO::new)
                .toList();

        List<RestaurantInfoDTO> fastFoods = allRestaurants
                .get(CategoryEnum.FAST_FOOD)
                .stream()
                .map(RestaurantInfoDTO::new)
                .toList();

        List<RestaurantInfoDTO> fineDining = allRestaurants
                .get(CategoryEnum.FINE_DINING)
                .stream()
                .map(RestaurantInfoDTO::new)
                .toList();

        model.addAttribute("cafesData", cafes);
        model.addAttribute("pubsData", pubs);
        model.addAttribute("fastFoodsData", fastFoods);
        model.addAttribute("fineDiningData", fineDining);

        return "home";
    }
}
