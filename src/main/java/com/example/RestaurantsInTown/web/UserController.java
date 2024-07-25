package com.example.RestaurantsInTown.web;

import com.example.RestaurantsInTown.model.dto.UserRegistrationDTO;
import com.example.RestaurantsInTown.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegistrationDTO registration() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO){
        userService.registerUser(userRegistrationDTO);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
