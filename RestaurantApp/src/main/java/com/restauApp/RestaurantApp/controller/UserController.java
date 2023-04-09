package com.restauApp.RestaurantApp.controller;

import com.restauApp.RestaurantApp.model.User;
import com.restauApp.RestaurantApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("restapp/v1.0/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/{user_id}")
    public Optional<User> showUser(@PathVariable Long user_id){
        return service.getUserById(user_id);
    }

    @GetMapping("/All")
    public List<User> showAllUser(){
        return service.getAllUser();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @PutMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return service.removeUser(userId);
    }

}
