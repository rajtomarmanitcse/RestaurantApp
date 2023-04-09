package com.restauApp.RestaurantApp.serviceImpl;

import com.restauApp.RestaurantApp.Repository.UserRepository;
import com.restauApp.RestaurantApp.model.Login;
import com.restauApp.RestaurantApp.model.User;
import com.restauApp.RestaurantApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long user_id) {
        Optional<User> tempUser =  repository.findById(user_id);

        if(tempUser.get().getIsActive())
            return tempUser;
        else
            return null;
    }

    @Override
    public List<User> getAllUser() {
        List<User> tempUsers =  repository.findAll();
        return tempUsers
                .stream()
                .filter(c -> c.getIsActive() == true)
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        if(user.getUserId() != null) {
            Optional<User> tempUser = repository.findById(user.getUserId());

            if (tempUser.isPresent() && tempUser.get().getIsActive()) {
                if (!user.getCity().equals(tempUser.get().getCity())) {
                    tempUser.get().setCity(user.getCity());
                }

                Login login = tempUser.get().getLogin();

                if(!user.getLogin().getPassWord().equals(login.getPassWord())){
                    tempUser.get().getLogin().setPassWord(user.getLogin().getPassWord());
                }
                return repository.save(tempUser.get());

            } else
                return null;
        }


        return null;
    }

    @Override
    public String removeUser(Long userId) {

            Optional<User> tempUser = repository.findById(userId);

            if(tempUser.isPresent() && tempUser.get().getIsActive()){
                tempUser.get().setIsActive(false);
                repository.save(tempUser.get());
                return "User name:- " + tempUser.get().getFirstName() + tempUser.get().getMiddleName() + tempUser.get().getLastName() +" is deleted";
            }else
                return "User Not Found !";

    }
}
