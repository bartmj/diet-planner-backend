package com.example.dietplanner.favourites.domain.port;

import com.example.dietplanner.favourites.domain.Favourite;
import com.example.dietplanner.foods.domain.Food;

import java.util.List;

public interface FavouriteService {

    Long saveFavourite(Food food, Long userId);

    List<Favourite> getAllForUser(Long userId);
}
