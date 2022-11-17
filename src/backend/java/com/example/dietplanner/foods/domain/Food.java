package com.example.dietplanner.foods.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Food {

    Long id;
    String name;
    Float weight;
    Float proteinPer100g;
    Float fatsPer100g;
    Float kcalPer100g;
    Float proteinTotal;
    Float fatsTotal;
    Float kcalTotal;

}
