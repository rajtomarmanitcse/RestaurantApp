package com.restauApp.RestaurantApp.serviceImpl;

import com.restauApp.RestaurantApp.Repository.RestaurantRepository;
import com.restauApp.RestaurantApp.model.Login;
import com.restauApp.RestaurantApp.model.Restaurant;
import com.restauApp.RestaurantApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Override
    public String removeRestaurant(Long restaurantId) {
        Optional<Restaurant> tempRestaurant = repository.findById(restaurantId);

        if(tempRestaurant.isPresent() && !tempRestaurant.get().getIsDeleted()){
            tempRestaurant.get().setIsDeleted(true);
            tempRestaurant.get().setIsOpen(false);
            repository.save(tempRestaurant.get());
            return "Restaurant name:- " + tempRestaurant.get().getName() +" is deleted";
        }else
            return "Restaurant Not Found !";
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        Optional<Restaurant> tempRestaurant =  repository.findById(restaurantId);

        if(!tempRestaurant.get().getIsDeleted())
            return tempRestaurant;
        else
            return null;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        if(restaurant.getRestaurantId() != null){
            Optional<Restaurant> tempRestaurant = repository.findById(restaurant.getRestaurantId());

            if(tempRestaurant.isPresent() && !tempRestaurant.get().getIsDeleted() ){
                if(restaurant.getIsOpen() != tempRestaurant.get().getIsOpen())
                    tempRestaurant.get().setIsOpen(restaurant.getIsOpen());

                if(! restaurant.getAddress().equals(tempRestaurant.get().getAddress()))
                    tempRestaurant.get().setAddress(restaurant.getAddress());

                if(!restaurant.getName().equals(tempRestaurant.get().getName()))
                    tempRestaurant.get().setName(restaurant.getName());

                if(!restaurant.getLocation().equals(tempRestaurant.get().getLocation()))
                    tempRestaurant.get().setLocation(restaurant.getLocation());

                Login login = tempRestaurant.get().getLogin();

                if(!restaurant.getLogin().getPassWord().equals(login.getPassWord()))
                    tempRestaurant.get().getLogin().setPassWord(restaurant.getLogin().getPassWord());


                return repository.save(tempRestaurant.get());

            }else
                return null;
        }

        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> tempRestaurants =  repository.findAll();
        return tempRestaurants.stream().filter(c -> c.getIsDeleted() == false).collect(Collectors.toList());
    }

    @Override
    public Restaurant isOpenRestaurant(Long restaurantId) {
        Optional<Restaurant> tempRestaurant = repository.findById(restaurantId);

        if(tempRestaurant.isPresent() && !tempRestaurant.get().getIsDeleted()){
            tempRestaurant.get().setIsOpen(!tempRestaurant.get().getIsOpen());
            return repository.save(tempRestaurant.get());
        }else
            return null;
    }
}
