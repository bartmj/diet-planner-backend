package com.example.dietplanner.foods.adapters.persistence;

import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FoodPersistenceAdapter implements FoodRepository {

    @Override
    public Long saveFood(Food food) {
        return null;
    }
}
