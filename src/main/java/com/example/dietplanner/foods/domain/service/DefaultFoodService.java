package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.domain.model.User;
import com.example.dietplanner.user.adapters.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class DefaultFoodService implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public DefaultFoodService(FoodRepository foodRepository, UserRepository userRepository) {
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long saveFood(Food food, Long userId) {

        var userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            food.setUserId(userId);
            FoodEntity foodEntity = foodRepository.saveFood(food);
            User user = userOptional.get();
            user.addFood(foodEntity);
            userRepository.save(user);
            return foodEntity.getId();
        }
        return null;
    }

    @Override
    public List<Food> getAll(Long userId) {
        return foodRepository.getAll(userId);
    }

    @Transactional
    @Override
    public boolean delete(Long foodId, Long userId) {
        var userOptional = userRepository.findById(userId);
        FoodEntity foodEntity;
        try {
            foodEntity = foodRepository.getReferenceById(foodId);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException();
        }
        userOptional.get().deleteFood(foodEntity);
        return foodRepository.deleteFood(foodEntity.getId());
    }
}
