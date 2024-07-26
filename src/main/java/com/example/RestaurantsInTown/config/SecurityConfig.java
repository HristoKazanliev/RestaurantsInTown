package com.example.RestaurantsInTown.config;

import com.example.RestaurantsInTown.repository.UserRepository;
import com.example.RestaurantsInTown.service.RestaurantsInTownUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(
                        // Config which URL is available to all the users - authorization
                        authorizeRequests ->
                                authorizeRequests
                                        //all static resources are available (css, img, js) to all the users
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        // all the users
                                        .requestMatchers("/", "/users/login", "/users/register").permitAll()
                                        // all other URl-s should be authenticated
                                        .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                // custom login form
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                // successful login
                                .defaultSuccessUrl("/home", true)
                                // login fails
                                .failureForwardUrl("/users/login-error")
                )
                .logout(logout ->
                        logout
                                // custom logout
                                .logoutUrl("/users/logout")
                                // successful logout
                                .logoutSuccessUrl("/")
                                // invalidate session after logout
                                .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public RestaurantsInTownUserDetailsService userDetailsService(UserRepository userRepository) {
        return new RestaurantsInTownUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
