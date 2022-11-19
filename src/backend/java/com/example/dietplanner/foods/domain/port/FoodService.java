package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodService {

    Long saveFood(Food food);

    List<Food> getAll();

    boolean delete(Long id);
}