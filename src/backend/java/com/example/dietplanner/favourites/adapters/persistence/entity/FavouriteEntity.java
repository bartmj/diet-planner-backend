package com.example.dietplanner.favourites.adapters.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "favourites")
@AllArgsConstructor
@Getter
public class FavouriteEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float proteinPer100g;
    private Float fatsPer100g;
    private Float kcalPer100g;
    private Float carbsPer100g;
    private Long userId;
}
