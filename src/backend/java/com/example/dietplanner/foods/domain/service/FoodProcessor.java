package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.model.User;
import com.example.dietplanner.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public Long saveFood(Food food) {
        var userOptional = userRepository.findById(food.getUserId());

        if (userOptional.isPresent()) {
            FoodEntity foodEntity = foodRepository.saveFood(food);
            User user = userOptional.get();
            user.addFood(foodEntity);
            userRepository.save(user);
            return foodEntity.getId();
        }

        throw new RuntimeException("Kuka la baka lala");
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return foodRepository.deleteFood(id);
    }
}
