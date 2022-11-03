package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodRepository {

    Long saveFood(Food food);

    List<Food> getAll();
}
