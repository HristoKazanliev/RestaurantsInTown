package com.example.RestaurantsInTown.service;

import com.example.RestaurantsInTown.model.dto.UserRegistrationDTO;
import com.example.RestaurantsInTown.model.entity.UserEntity;
import com.example.RestaurantsInTown.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedUser = modelMapper.map(userRegistrationDTO, UserEntity.class);

        mappedUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedUser;
    }
}
