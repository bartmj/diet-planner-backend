package com.example.dietplanner.foods.config;

import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import com.example.dietplanner.favourites.domain.service.FavouritesProcessor;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.service.DefaultFoodService;
import com.example.dietplanner.user.adapters.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfiguration {

    @Bean(name = "foodService")
    public DefaultFoodService getFoodProcessor(FoodRepository foodRepository, UserRepository userRepository) {
        return new DefaultFoodService(foodRepository, userRepository);
    }

    @Bean(name = "favouritesService")
    public FavouritesProcessor getFavouritesProcessor(FavouriteRepository favouriteRepository) {
        return new FavouritesProcessor(favouriteRepository);
    }
}
