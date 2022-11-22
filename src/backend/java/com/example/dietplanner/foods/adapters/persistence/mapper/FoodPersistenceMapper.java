package com.example.dietplanner.foods.adapters.persistence.mapper;

import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                food.getCarbsPer100g(),
                food.getProteinTotal(),
                food.getFatsTotal(),
                food.getCarbsTotal(),
                food.getKcalTotal(),
                food.getUserId());
    }

    @Override
    public Food toDomain(FoodEntity foodEntity) {
        return Food.builder()
                .id(foodEntity.getId())
                .name(foodEntity.getName())
                .weight(foodEntity.getWeight())
                .proteinPer100g(foodEntity.getProteinPer100g())
                .fatsPer100g(foodEntity.getFatsPer100g())
                .kcalPer100g(foodEntity.getKcalPer100g())
                .carbsPer100g(foodEntity.getCarbohydratesPer100g())
                .proteinTotal(foodEntity.getProteinTotal())
                .carbsTotal(foodEntity.getCarbohydratesTotal())
                .fatsTotal(foodEntity.getFatsTotal())
                .kcalTotal(foodEntity.getKcalTotal())
                .userId(foodEntity.getUserId())
                .build();

    }

    @Override
    public List<Food> toDomain(List<FoodEntity> foodEntityList) {
        List<Food> foods = new ArrayList<>();
        for (final FoodEntity entity : foodEntityList) {
            foods.add(toDomain(entity));
        }
        return foods;
    }
}
