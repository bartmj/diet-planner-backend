package com.example.dietplanner.foods.adapters.persistence;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.adapters.repository.JpaFoodRepository;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FoodPersistenceAdapter implements FoodRepository {

    private final JpaFoodRepository jpaFoodRepository;
    private final FoodPersistenceMapper mapper;

    @Override
    public FoodEntity saveFood(Food food) {
        var entity = mapper.toEntity(food);
        return jpaFoodRepository.save(entity);
    }

    @Override
    public List<Food> getAll() {
        var foodEntities = jpaFoodRepository.findAll();
        return mapper.toDomain(foodEntities);
    }

    @Override
    public boolean deleteFood(Long id) {
        var byId = jpaFoodRepository.findById(id);
        if (byId.isPresent()) {
            jpaFoodRepository.delete(byId.get());
            return true;
        }
        return false;
    }
}
