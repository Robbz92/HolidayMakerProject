package com.example.demo.services;

import com.example.demo.entities.Favorite;
import com.example.demo.entities.User;
import com.example.demo.repositories.FavoriteRepo;
import java.util.List;
import java.util.Map;

import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepo favoriteRepo;

    @Autowired
    private UserService userService;


    public List<Map> getMyFavorites() {
        User user = userService.whoAmI();
        long userId = user.getId();
        return favoriteRepo.getMyFavorites(userId);
    }

    public void saveFavorite(Favorite favorite){
        favoriteRepo.save(favorite);
    }
}
