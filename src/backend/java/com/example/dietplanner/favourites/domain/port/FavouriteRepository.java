package com.example.dietplanner.favourites.domain.port;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;

public interface FavouriteRepository {
    FavouriteEntity saveFavourite(Favourite favourite);
}
