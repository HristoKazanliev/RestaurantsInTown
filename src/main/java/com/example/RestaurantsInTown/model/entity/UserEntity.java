package com.example.RestaurantsInTown.model.entity;

import com.example.RestaurantsInTown.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "addedBy")
    private List<Restaurant> addedRestaurants;

    @ManyToMany
    private List<Restaurant> favouriteRestaurants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity() {
        addedRestaurants = new ArrayList<>();
        favouriteRestaurants = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public List<Restaurant> getAddedRestaurants() {
        return addedRestaurants;
    }

    public void setAddedRestaurants(List<Restaurant> addedRestaurants) {
        this.addedRestaurants = addedRestaurants;
    }

    public List<Restaurant> getFavouriteRestaurants() {
        return favouriteRestaurants;
    }

    public void setFavouriteRestaurants(List<Restaurant> favouriteRestaurants) {
        this.favouriteRestaurants = favouriteRestaurants;
    }
}
