package com.restauApp.RestaurantApp.serviceImpl;

import com.restauApp.RestaurantApp.Repository.UserRepository;
import com.restauApp.RestaurantApp.model.User;
import com.restauApp.RestaurantApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long user_id) {
        return repository.findById(user_id);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if(user.getUserId() != null){
            Optional<User> tempUser = repository.findById(user.getUserId());

            if(tempUser.isPresent() && !user.getCity().equals(tempUser.get().getCity())){
                tempUser.get().setCity(user.getCity());
                return repository.save(tempUser.get());
            }else
                return null;
        }

        return null;
    }
}
