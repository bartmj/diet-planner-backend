package com.example.dietplanner.favourites.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Favourite {

    private Long id;
    private String name;
    private Float proteinPer100g;
    private Float fatsPer100g;
    private Float carbsPer100g;
    private Float kcalPer100g;
    private Long userId;
}
