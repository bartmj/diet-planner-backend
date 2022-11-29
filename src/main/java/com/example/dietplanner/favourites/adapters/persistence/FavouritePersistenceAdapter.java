package com.example.dietplanner.favourites.adapters.persistence;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.favourites.adapters.persistence.mapper.FavouritePersistenceMapper;
import com.example.dietplanner.favourites.adapters.repository.JpaFavouriteRepository;
import com.example.dietplanner.favourites.domain.Favourite;
import com.example.dietplanner.favourites.domain.port.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FavouritePersistenceAdapter implements FavouriteRepository {

    private final JpaFavouriteRepository jpaFavouriteRepository;
    private final FavouritePersistenceMapper mapper;

    @Override
    public FavouriteEntity saveFavourite(Favourite favourite) {
        return jpaFavouriteRepository.save(mapper.toEntity(favourite));
    }

    @Override
    public List<Favourite> getAllByUserId(Long userId) {
        return mapper.toDomain(jpaFavouriteRepository.getAllByUserId(userId));
    }
}
