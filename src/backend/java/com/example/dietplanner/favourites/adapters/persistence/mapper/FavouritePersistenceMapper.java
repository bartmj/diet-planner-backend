package com.example.dietplanner.favourites.adapters.persistence.mapper;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.domain.Favourite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavouritePersistenceMapper {
    public FavouriteEntity toEntity(Favourite favourite) {
        return new FavouriteEntity(favourite.getId(), favourite.getName(), favourite.getProteinPer100g(), favourite.getFatsPer100g(), favourite.getKcalPer100g(), favourite.getUserId());
    }

    public Favourite toDomain(FavouriteEntity favourite) {
        return Favourite.builder()
                .name(favourite.getName())
                .proteinPer100g(favourite.getProteinPer100g())
                .fatsPer100g(favourite.getFatsPer100g())
                .kcalPer100g(favourite.getKcalPer100g())
                .userId(favourite.getUserId())
                .build();
    }

    public List<Favourite> toDomain(List<FavouriteEntity> favouriteEntitiesByUserId) {
            List<Favourite> favourites = new ArrayList<>();
            for (final FavouriteEntity entity : favouriteEntitiesByUserId) {
                favourites.add(toDomain(entity));
            }
            return favourites;
        }
    }

