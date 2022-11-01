package com.example.dietplanner.foods.adapters;

import com.example.dietplanner.foods.adapters.rest.dto.FoodDto;
import com.example.dietplanner.foods.domain.port.FoodRestMapper;
import com.example.dietplanner.foods.domain.port.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService service;
    private final FoodRestMapper foodRestMapper;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Long> sendExercise(@RequestBody FoodDto foodDto) {
        var food = foodRestMapper.toDomain(foodDto);
        var aLong = service.saveFood(food);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aLong);
    }

}
