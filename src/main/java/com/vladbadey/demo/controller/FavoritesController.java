package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.mapper.CompositionMapper;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FavoritesController {

    private final UserRepository userRepository;

    private final CompositionRepository compositionRepository;

    private final CompositionMapper compositionMapper;

    @GetMapping("/{id}/all")
    public Set<CompositionResponseDto> getAllFavorites(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        Set<CompositionResponseDto> savedCompositions = new HashSet<>();
        Set<Composition> compositions = user.getFavoritesCompositions();
        for (Composition c : compositions) {
            savedCompositions.add(compositionMapper.toResponseDto(c));
        }
        return savedCompositions;
    }

    @GetMapping("/all")
    public Set<CompositionResponseDto> getAllFavoritesByName(@RequestParam String name) {
        User user = userRepository.findByUsername(name).get();
        Set<CompositionResponseDto> savedCompositions = new HashSet<>();
        Set<Composition> compositions = user.getFavoritesCompositions();
        for (Composition c : compositions) {
            savedCompositions.add(compositionMapper.toResponseDto(c));
        }
        return savedCompositions;
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<?> addNewFavorite(@PathVariable Long id, @RequestParam(name = "id") Long composition_id) {
        Set<Composition> compositions = userRepository.getById(id).getFavoritesCompositions();
        compositions.add(compositionRepository.getById(composition_id));
        return ResponseEntity.ok(userRepository.save(userRepository.getById(id)));
    }

    @PostMapping("/add")
    public void addNewFavoriteByName(@RequestParam String username, @RequestParam String compositionName) {
        Set<Composition> compositions = userRepository.findByUsername(username).get().getFavoritesCompositions();
        compositions.add(compositionRepository.findByName(compositionName));
        userRepository.save(userRepository.findByUsername(username).get());
    }


    @DeleteMapping("/{id}/delete")
    public void deleteFavorite(@PathVariable Long id, @RequestParam(name = "id") Long composition_id) {
        Set<Composition> compositions = userRepository.getById(id).getFavoritesCompositions();
        compositions.remove(compositionRepository.getById(composition_id));
        userRepository.save(userRepository.getById(id));
    }
}