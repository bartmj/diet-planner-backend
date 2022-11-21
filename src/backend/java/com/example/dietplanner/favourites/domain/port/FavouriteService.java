package com.example.dietplanner.favourites.domain.port;

import com.example.dietplanner.foods.domain.Food;

public interface FavouriteService {
    Long saveFavourite(Food food, Long userId);
}
