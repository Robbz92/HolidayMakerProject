package com.example.demo.controllers;

import com.example.demo.entities.Favorite;
import com.example.demo.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("auth/favorites")
    public List<Map> getMyFavorites(){return favoriteService.getMyFavorites();}

    @PostMapping("auth/favorites")
    public void saveFavorite(@RequestBody Favorite favorite){favoriteService.saveFavorite(favorite);}

}