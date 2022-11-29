package com.example.dietplanner.favourites.domain.port;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;

import java.util.List;

public interface FavouriteRepository {

    FavouriteEntity saveFavourite(Favourite favourite);

    List<Favourite> getAllByUserId(Long userId);
}
