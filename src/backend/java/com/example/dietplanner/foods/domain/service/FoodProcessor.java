package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.model.User;
import com.example.dietplanner.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FoodProcessor implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public FoodProcessor(FoodRepository foodRepository, UserRepository userRepository) {
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
            return userRepository.save(user).getId();
        }
        return null;
    }

    @Override
    public List<Food> getAll(Long userId) {
        return foodRepository.getAll(userId);
    }

    @Override
    public boolean delete(Long id, Long userId) {
        var userOptional = userRepository.findById(userId);
        userOptional.get().deleteFood(id);
        return foodRepository.deleteFood(id);
    }
}
