package com.example.dietplanner.favourites.adapters.repository;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
}
