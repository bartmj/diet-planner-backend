package com.example.dietplanner.favourites.adapters.persistence.mapper;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;
import org.springframework.stereotype.Component;

@Component
public class FavouritePersistenceMapper {
    public FavouriteEntity toEntity(Favourite favourite) {
        return new FavouriteEntity(favourite.getId(), favourite.getName(), favourite.getProteinPer100g(), favourite.getFatsPer100g(), favourite.getKcalPer100g(), favourite.getUserId());
    }
}
