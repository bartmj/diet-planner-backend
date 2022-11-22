package com.example.dietplanner.favourites.adapters.repository;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
    List<FavouriteEntity> getAllByUserId(Long id);
}
