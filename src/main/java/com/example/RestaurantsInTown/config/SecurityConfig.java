package com.example.RestaurantsInTown.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                                .defaultSuccessUrl("/", true)
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


}
