package com.example.dietplanner.favourites.adapters.rest;

import com.example.dietplanner.favourites.domain.Favourite;
import com.example.dietplanner.favourites.domain.port.FavouriteService;
import com.example.dietplanner.user.domain.port.IAuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("favourites")
@AllArgsConstructor
public class FavouritesController {

    private final FavouriteService favouriteService;
    private final IAuthenticationFacade authFacade;

    @GetMapping("/user")
    public ResponseEntity<?> getAllUserFavourites() {
        Long userId = authFacade.getUserId();
        List<Favourite> allForUser = favouriteService.getAllForUser(userId);
        return ResponseEntity.ok()
                .body(allForUser);
    }
}
















