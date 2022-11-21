package com.example.dietplanner.foods.adapters.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Table(name = "foods")
public class FoodEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float weight;
    private Float proteinPer100g;
    private Float fatsPer100g;
    private Float kcalPer100g;
    private Float proteinTotal;
    private Float fatsTotal;
    private Float kcalTotal;
    private Long userId;
}
