package com.example.dietplanner.foods.adapters;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.Food;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import com.example.dietplanner.foods.domain.port.FoodService;
import com.example.dietplanner.user.port.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calc")
public class FoodController {

    private final FoodService service;
    private final FoodRestMapper foodRestMapper;
    private final IAuthenticationFacade authFacade;

    @CrossOrigin
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Long> sendFood(@Valid @RequestBody FoodDto foodDto) {
        Long userId = authFacade.getUserId();
        var food = foodRestMapper.toDomain(foodDto);
        var aLong = service.saveFood(food, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aLong);
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
    @DeleteMapping(value = "/all/{id}")
    public ResponseEntity<Long> deleteFood(@PathVariable Long foodId) {
        Long userId = authFacade.getUserId();
        var isRemoved = service.delete(foodId, userId);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodId, HttpStatus.OK);
    }

}












