package com.example.dietplanner.foods.adapters.persistence;

import com.example.dietplanner.foods.adapters.repository.JpaFoodRepository;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FoodPersistenceAdapter implements FoodRepository {

    private final JpaFoodRepository jpaFoodRepository;
    private final FoodPersistenceMapper mapper;

    @Override
    public Long saveFood(Food food) {
        var foodEntity = mapper.toEntity(food);
        return jpaFoodRepository.save(foodEntity).getId();
    }
}
