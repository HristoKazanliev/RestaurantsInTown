package com.example.RestaurantsInTown.web;

import com.example.RestaurantsInTown.model.dto.RestaurantAddDTO;
import com.example.RestaurantsInTown.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ModelAttribute("restaurantData")
    public RestaurantAddDTO restaurantData() {
        return new RestaurantAddDTO();
    }

    @GetMapping("/add")
    public String addRestaurant() {
        return "restaurant-add";
    }

    @PostMapping("/add")
    public String addRestaurant(@Valid RestaurantAddDTO restaurantAddDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("restaurantData", restaurantAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.restaurantData", bindingResult);

            return "redirect:/restaurants/add";
        }

        boolean success =  restaurantService.addRestaurant(restaurantAddDTO, userDetails);
        if (!success) {
            redirectAttributes.addFlashAttribute("restaurantData", restaurantAddDTO);

            return "redirect:/restaurants/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String restaurantDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("restaurantDetails", restaurantService.getRestaurantDetails(id));

        return "restaurant-details";
    }
}
