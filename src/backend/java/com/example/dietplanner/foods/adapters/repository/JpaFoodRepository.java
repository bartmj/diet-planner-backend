package com.example.dietplanner.foods.adapters.repository;

import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFoodRepository extends JpaRepository<FoodEntity, Long> {

    List<FoodEntity> getAllByUserId(Long id);
}
