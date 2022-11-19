package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.model.User;
import com.example.dietplanner.user.repository.UserRepository;

import java.util.List;

public class FoodProcessor implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final FoodPersistenceMapper mapper;

    public FoodProcessor(FoodRepository foodRepository, UserRepository userRepository, FoodPersistenceMapper mapper) {
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

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

        throw new RuntimeException("Ups no username!");
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
