package com.vladbadey.demo.controller;

import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FavoritesController {

    private final UserRepository userRepository;

    private final CompositionRepository compositionRepository;

    @GetMapping("/{id}/all")
    public Set<Composition> getAllFavorites(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return user.getFavoritesCompositions();
    }

    @PostMapping("/{id}/add")
    public void addNewFavorite(@PathVariable Long id, @RequestParam(name = "id") Long composition_id) {
        Set<Composition> compositions = userRepository.getById(id).getFavoritesCompositions();
        compositions.add(compositionRepository.getById(composition_id));
        userRepository.save(userRepository.getById(id));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteFavorite(@PathVariable Long id, @RequestParam(name = "id") Long composition_id) {
        Set<Composition> compositions = userRepository.getById(id).getFavoritesCompositions();
        compositions.remove(compositionRepository.getById(composition_id));
        userRepository.save(userRepository.getById(id));
    }
}
