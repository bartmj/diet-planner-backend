package com.example.dietplanner.favourites.domain.service;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;
import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import com.example.dietplanner.favourites.domain.port.FavouriteService;
import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public class FavouritesProcessor implements FavouriteService {

    private final FavouriteRepository favouriteRepository;

    public FavouritesProcessor(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public Long saveFavourite(Food food, Long userId) {
        FavouriteEntity favouriteEntity = favouriteRepository.saveFavourite(
                Favourite.builder()
                        .id(food.getId())
                        .name(food.getName())
                        .proteinPer100g(food.getProteinPer100g())
                        .fatsPer100g(food.getFatsPer100g())
                        .kcalPer100g(food.getKcalPer100g())
                        .carbsPer100g(food.getCarbsPer100g())
                        .userId(userId)
                        .build());
        return favouriteEntity.getId();
    }

    @Override
    public List<Favourite> getAllForUser(Long userId) {
        return favouriteRepository.getAllByUserId(userId);
    }
}