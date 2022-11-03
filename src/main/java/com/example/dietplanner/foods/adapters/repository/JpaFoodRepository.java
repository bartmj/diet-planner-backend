package com.example.dietplanner.foods.adapters.repository;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFoodRepository extends JpaRepository<FoodEntity, Long> {
}
