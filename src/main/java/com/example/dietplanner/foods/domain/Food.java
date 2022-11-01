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
    Integer protein;
    Integer fats;

}
