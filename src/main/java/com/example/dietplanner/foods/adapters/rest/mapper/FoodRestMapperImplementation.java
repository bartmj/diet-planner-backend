package com.example.dietplanner.foods.adapters.rest.mapper;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import org.springframework.stereotype.Service;

@Service
public class FoodRestMapperImplementation implements FoodRestMapper {

    @Override
    public Food toDomain(FoodDto foodDto) {
        return null;
    }

    @Override
    public FoodDto toDto(Food food) {
        return null;
    }
}
