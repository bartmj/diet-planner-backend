package com.example.dietplanner.foods.adapters.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer weight;
    private Integer proteinPer100g;
    private Integer fatsPer100g;
    private Integer kcalPer100g;
    private Integer proteinTotal;
    private Integer fatsTotal;
    private Integer kcalTotal;

}
