package com.example.dietplanner.foods.adapters.rest.mapper;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import org.springframework.stereotype.Service;

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
        return null;
    }
}

//    Long id;
//    String name;
//    Integer weight;
//    Integer proteinPer100g;
//    Integer fatsPer100g;
//    Integer kcalPer100g;
//    Integer proteinTotal;
//    Integer fatsTotal;
//    Integer kcalTotal;