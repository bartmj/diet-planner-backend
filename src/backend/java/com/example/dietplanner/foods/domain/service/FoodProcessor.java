package com.example.dietplanner.foods.domain.service;

import com.example.dietplanner.foods.adapters.entity.FoodEntity;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodPersistenceMapper;
import com.example.dietplanner.foods.domain.port.FoodRepository;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.model.User;
import com.example.dietplanner.user.repository.UserRepository;
import com.example.dietplanner.user.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    public Long saveFood(Food food) {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long principalId = principal.getId();

        var userOptional = userRepository.findById(principalId);

        if (userOptional.isPresent()) {
            food.setUserId(principalId);
            FoodEntity foodEntity = foodRepository.saveFood(food);
            User user = userOptional.get();
            user.addFood(foodEntity);
            userRepository.save(user);
            return foodEntity.getId();
        }

        throw new RuntimeException("Ups no username!");
    }

    @Override
    public List<Food> getAll() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return foodRepository.getAll(principal.getId());
    }

    @Override
    public boolean delete(Long id) {
        return foodRepository.deleteFood(id);
    }
}
