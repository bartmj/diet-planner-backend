package com.example.dietplanner.foods.config;

import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import com.example.dietplanner.favourites.domain.service.FavouritesProcessor;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.service.FoodProcessor;
import com.example.dietplanner.user.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfiguration {

    @Bean(name = "foodService")
    public FoodProcessor getFoodProcessor(FoodRepository foodRepository, UserRepository userRepository) {
        return new FoodProcessor(foodRepository, userRepository);
    }

    @Bean(name = "favouritesService")
    public FavouritesProcessor getFavouritesProcessor(FavouriteRepository favouriteRepository) {
        return new FavouritesProcessor(favouriteRepository);
    }
}