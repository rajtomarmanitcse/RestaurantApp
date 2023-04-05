package com.restauApp.RestaurantApp.service;

import com.restauApp.RestaurantApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);

    Optional<User> getUserById(Long user_id);

    List<User> getAllUser();

    User updateUser(User user);
}
