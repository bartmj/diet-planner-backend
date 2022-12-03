package com.example.dietplanner.foods.adapters.rest;

import com.example.dietplanner.favourites.domain.port.FavouriteService;
import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.domain.payload.MessageResponse;
import com.example.dietplanner.user.domain.port.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/calc")
public class FoodController {

    private final FoodService service;
    private final FavouriteService favouriteService;
    private final FoodRestMapper foodRestMapper;
    private final IAuthenticationFacade authFacade;


    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> sendFood(@Valid @RequestBody FoodDto foodDto) {
        Long userId = authFacade.getUserId();

        var food = foodRestMapper.toDomain(foodDto);
        Long savedToFoodsId = service.saveFood(food, userId);

        Long savedToFavourites = null;
        if (foodDto.getIfFavourite()) {
            savedToFavourites = favouriteService.saveFavourite(food, userId);
        }
        if (savedToFoodsId != null && savedToFavourites != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new MessageResponse(String.format("Food with id %s been added to favourites.", savedToFoodsId), savedToFoodsId));
        }
        if (savedToFoodsId != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new MessageResponse(String.format("Food with  id %s been added to favourites.", savedToFoodsId), savedToFoodsId));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Internal server error."));
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public ResponseEntity<List<FoodDto>> getAllFoods() {
        Long userId = authFacade.getUserId();
        List<Food> foods = service.getAll(userId);
        var foodDtoList = foodRestMapper.toDto(foods);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodDtoList);
    }

    @CrossOrigin
    @DeleteMapping(value = "/all/{foodId}")
    public ResponseEntity<Long> deleteFood(@PathVariable Long foodId) {
        Long userId = authFacade.getUserId();
        var isRemoved = service.delete(foodId, userId);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodId, HttpStatus.OK);
    }
}












