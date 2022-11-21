package com.example.dietplanner.favourites.domain.service;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;
import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import com.example.dietplanner.favourites.domain.port.FavouriteService;
import com.example.dietplanner.foods.domain.Food;

public class FavouritesProcessor implements FavouriteService {

    private final FavouriteRepository favouriteRepository;

    public FavouritesProcessor(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public Long saveFavourite(Food food, Long userId) {
        FavouriteEntity favouriteEntity = favouriteRepository.saveFavourite(
                Favourite.builder()
                        .name(food.getName())
                        .proteinPer100g(food.getProteinPer100g())
                        .fatsPer100g(food.getFatsPer100g())
                        .kcalPer100g(food.getKcalPer100g())
                        .userId(userId)
                        .build());
        return favouriteEntity.getId();
    }
}
//    private Long id;
//    private String name;
//    private Float proteinPer100g;
//    private Float fatsPer100g;
//    private Float kcalPer100g;
//    private Long userId;
//public class FoodProcessor implements FoodService {
//
//    private final FoodRepository foodRepository;
//    private final UserRepository userRepository;
//
//    public FoodProcessor(FoodRepository foodRepository, UserRepository userRepository) {
//        this.foodRepository = foodRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Transactional
//    @Override
//    public Boolean saveFood(Food food, Long userId) {
//
//        var userOptional = userRepository.findById(userId);
//
//        if (userOptional.isPresent()) {
//            food.setUserId(userId);
//            FoodEntity foodEntity = foodRepository.saveFood(food);
//            User user = userOptional.get();
////            addFavourite(food, userId, foodEntity, user);
//            user.addFood(foodEntity);
//            userRepository.save(user);
//
//            return true;
//        }
//        return false;
//    }
//
////    private void addFavourite(Food food, Long userId, FoodEntity foodEntity, User user) {
////        FavouriteEntity favouriteEntity = favouriteRepository.saveFavourite(Favourite.builder()
////                .name(food.getName())
////                .proteinPer100g(food.getProteinPer100g())
////                .fatsPer100g(foodEntity.getFatsPer100g())
////                .kcalPer100g(food.getKcalPer100g())
////                .userId(userId)
////                .build());
////        user.addFood(favouriteEntity);
////    }
//
//    @Override
//    public List<Food> getAll(Long userId) {
//        return foodRepository.getAll(userId);
//    }
//
//    @Override
//    public boolean delete(Long id, Long userId) {
//        var userOptional = userRepository.findById(userId);
//        userOptional.get().deleteFood(id);
//        return foodRepository.deleteFood(id);
//    }
//}
