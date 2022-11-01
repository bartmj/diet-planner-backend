package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.domain.Food;

public interface FoodRepository {

    Long saveFood(Food food);

}
