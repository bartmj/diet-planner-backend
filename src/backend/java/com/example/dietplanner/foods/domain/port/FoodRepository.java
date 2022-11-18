package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodRepository {

    FoodEntity saveFood(Food food);

    List<Food> getAll();

    boolean deleteFood(Long id);
}
