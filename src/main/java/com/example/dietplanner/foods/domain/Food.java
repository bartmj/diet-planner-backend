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
    Integer weight;
    Integer proteinPer100g;
    Integer fatsPer100g;
    Integer kcalPer100g;
    Integer proteinTotal;
    Integer fatsTotal;
    Integer kcalTotal;

}
