package com.restauApp.RestaurantApp.service;

import com.restauApp.RestaurantApp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    String removeRestaurant(Long restaurantId);

    Restaurant addRestaurant(Restaurant restaurant);

    Optional<Restaurant> getRestaurantById(Long restaurantId);

    Restaurant updateRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant isOpenRestaurant(Long restaurantId);
}
