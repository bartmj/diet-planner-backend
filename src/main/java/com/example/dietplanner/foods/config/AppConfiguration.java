package com.example.dietplanner.foods.config;

import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.service.FoodProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfiguration {

    @Bean(name = "foodService")
    public FoodProcessor getFoodProcessor(FoodRepository foodRepository) {
        return new FoodProcessor(foodRepository);
    }
}
