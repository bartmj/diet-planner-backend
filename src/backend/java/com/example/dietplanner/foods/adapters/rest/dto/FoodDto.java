package com.example.dietplanner.foods.adapters.rest.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class FoodDto {

    Long id;
    @NotBlank
    String name;
    @NotNull
    Float weight;
    @NotNull
    Float proteinPer100g;
    @NotNull
    Float fatsPer100g;
    @NotNull
    Float kcalPer100g;
    @NotNull
    Float proteinTotal;
    @NotNull
    Float fatsTotal;
    @NotNull
    Float kcalTotal;
    Boolean ifFavourite;
}
