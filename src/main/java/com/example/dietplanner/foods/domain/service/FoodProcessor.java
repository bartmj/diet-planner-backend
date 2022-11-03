package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FoodProcessor implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Long saveFood(Food food) {
        return foodRepository.saveFood(food);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.getAll();
    }
}
