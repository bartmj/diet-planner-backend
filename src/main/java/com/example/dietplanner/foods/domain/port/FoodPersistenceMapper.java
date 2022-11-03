package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FoodPersistenceMapper {

    FoodEntity toEntity(Food food);

    Food toDomain(FoodEntity foodEntity);

    List<Food> toDomain(List<FoodEntity> foodEntityList);
}
