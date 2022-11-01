package com.example.dietplanner.foods.adapters.rest.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class FoodDto {

    Long id;
    @NotBlank
    String name;
    @NotNull
    Integer weight;
    @NotNull
    Integer proteinPer100g;
    @NotNull
    Integer fatsPer100g;
    @NotNull
    Integer kcalPer100g;
    @NotNull
    Integer proteinTotal;
    @NotNull
    Integer fatsTotal;
    @NotNull
    Integer kcalTotal;

    public FoodDto(Long id, String name, Integer weight, Integer proteinPer100g, Integer fatsPer100g, Integer kcalPer100g, Integer proteinTotal, Integer fatsTotal, Integer kcalTotal) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.proteinPer100g = proteinPer100g;
        this.fatsPer100g = fatsPer100g;
        this.kcalPer100g = kcalPer100g;
        this.proteinTotal = proteinTotal;
        this.fatsTotal = fatsTotal;
        this.kcalTotal = kcalTotal;
    }
}
