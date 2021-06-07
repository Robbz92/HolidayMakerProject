package com.example.demo.controllers;

import com.example.demo.entities.Favorite;
import com.example.demo.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorites")
    public List<Map> getMyFavorites(){return favoriteService.getMyFavorites();}

    @PostMapping("/favorites")
    public void saveFavorite(@RequestBody Favorite favorite){favoriteService.saveFavorite(favorite);}

    @DeleteMapping("/favorites/{id}")
    public void deleteFavorite(@PathVariable long id){favoriteService.deleteFavorite(id);}

}