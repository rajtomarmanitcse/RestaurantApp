package com.restauApp.RestaurantApp.controller;

import com.restauApp.RestaurantApp.model.Restaurant;
import com.restauApp.RestaurantApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("restapp/v1.0/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @PostMapping("/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant){
        return service.addRestaurant(restaurant);
    }

    @GetMapping("/{restaurantId}")
    public Optional<Restaurant> getRestaurant(@PathVariable Long restaurantId){
        return service.getRestaurantById(restaurantId);
    }

    @GetMapping("/All")
    public List<Restaurant> getAllRestaurants(){
        return service.getAllRestaurants();
    }

    @PutMapping("/update")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant){
        return service.updateRestaurant(restaurant);
    }

    @PutMapping("/isopen/{restaurantId}")
    public Restaurant isOpenRestaurant(@PathVariable Long restaurantId){
        return service.isOpenRestaurant(restaurantId);
    }

    @PutMapping("/delete/{restaurantId}")
    public String removeRestaurant(@PathVariable Long restaurantId){
        return service.removeRestaurant(restaurantId);
    }
}
