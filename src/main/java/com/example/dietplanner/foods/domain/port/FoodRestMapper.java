package com.example.dietplanner.foods.domain.port;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;


public interface FoodRestMapper {

    Food toDomain(FoodDto foodDto);

    FoodDto toDto(Food food);

}
