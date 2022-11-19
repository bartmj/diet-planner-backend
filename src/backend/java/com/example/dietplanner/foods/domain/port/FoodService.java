package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodService {

    Long saveFood(Food food, Long id);

    List<Food> getAll(Long id);

    boolean delete(Long foodId, Long userId);
}
