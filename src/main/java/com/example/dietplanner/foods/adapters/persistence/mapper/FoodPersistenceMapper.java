package com.example.dietplanner.foods.adapters.persistence.mapper;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
class FoodPersistenceMapperImplementation implements FoodPersistenceMapper {

    @Override
    public FoodEntity toEntity(Food food) {
        return new FoodEntity(food.getId(),
                food.getName(),
                food.getWeight(),
                food.getProteinPer100g(),
                food.getFatsPer100g(),
                food.getKcalPer100g(),
                food.getProteinTotal(),
                food.getFatsTotal(),
                food.getKcalTotal());
    }

    @Override
    public Food toDomain(FoodEntity foodEntity) {
        return null;
    }
}
