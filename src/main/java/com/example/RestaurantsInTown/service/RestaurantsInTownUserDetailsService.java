package com.example.RestaurantsInTown.service;

import com.example.RestaurantsInTown.model.entity.UserEntity;
import com.example.RestaurantsInTown.model.user.RestaurantsInTownUserDetails;
import com.example.RestaurantsInTown.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class RestaurantsInTownUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public RestaurantsInTownUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(RestaurantsInTownUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return new RestaurantsInTownUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                List.of()
        );
    }
}
