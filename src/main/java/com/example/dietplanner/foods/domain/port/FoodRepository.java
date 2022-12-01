package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodRepository {

    FoodEntity saveFood(Food food);

    List<Food> getAll(Long id);

    boolean deleteFood(Long id);

    FoodEntity getReferenceById(Long aLong);
}
