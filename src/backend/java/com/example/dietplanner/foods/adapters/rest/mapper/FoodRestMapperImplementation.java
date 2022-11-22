package com.example.dietplanner.foods.adapters.rest.mapper;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodRestMapperImplementation implements FoodRestMapper {

    @Override
    public Food toDomain(FoodDto foodDto) {

        return Food.builder()
                .id(foodDto.getId())
                .name(foodDto.getName())
                .weight(foodDto.getWeight())
                .proteinPer100g(foodDto.getProteinPer100g())
                .fatsPer100g(foodDto.getFatsPer100g())
                .kcalPer100g(foodDto.getKcalPer100g())
                .proteinTotal(foodDto.getProteinTotal())
                .fatsTotal(foodDto.getFatsTotal())
                .kcalTotal(foodDto.getKcalTotal())
                .build();
    }

    @Override
    public FoodDto toDto(Food food) {
        return FoodDto.builder()
                .id(food.getId())
                .name(food.getName())
                .weight(food.getWeight())
                .proteinPer100g(food.getProteinPer100g())
                .fatsPer100g(food.getFatsPer100g())
                .carbsPer100g(food.getCarbsPer100g())
                .kcalPer100g(food.getKcalPer100g())
                .proteinTotal(food.getProteinTotal())
                .fatsTotal(food.getFatsTotal())
                .kcalTotal(food.getKcalTotal())
                .build();
    }

    @Override
    public List<FoodDto> toDto(List<Food> foods) {
        var foodDtoList = new ArrayList<FoodDto>();
        for (final Food food : foods) {
            foodDtoList.add(toDto(food));
        }
        return foodDtoList;
    }
}