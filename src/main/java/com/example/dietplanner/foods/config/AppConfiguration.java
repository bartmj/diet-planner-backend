package com.example.dietplanner.foods.config;

import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import com.example.dietplanner.favourites.domain.service.FavouritesProcessor;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.service.DefaultFoodService;
import com.example.dietplanner.user.adapters.persistence.RoleRepository;
import com.example.dietplanner.user.adapters.persistence.UserRepository;
import com.example.dietplanner.user.domain.services.DefaultUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean(name = "userService")
    public DefaultUserService getDefaultUserService(RoleRepository roleRepository,
                                                    UserRepository userRepository,
                                                    PasswordEncoder encoder) {
        return new DefaultUserService(roleRepository, userRepository, encoder);
    }
}
