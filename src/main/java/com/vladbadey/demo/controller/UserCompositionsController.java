package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.service.UserCompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user/compositions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserCompositionsController {

    private final UserCompositionService userCompositionService;

    @GetMapping("/{id}/all")
    public ResponseEntity<?> getCompositions(@PathVariable Long id) {
        return ResponseEntity.ok(userCompositionService.findAllUserCompositions(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUserCompositions(@RequestParam String name) {
        return ResponseEntity.ok(userCompositionService.findAllUserCompositionsByName(name));
    }

    @GetMapping("/{id}/{composition_id}")
    public ResponseEntity<?> getComposition(@PathVariable Long id, @PathVariable Long composition_id) {
        return ResponseEntity.ok(userCompositionService.findUserComposition(composition_id));
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getCompositionUser(@RequestParam String name) {
        return ResponseEntity.ok(userCompositionService.findCompositionUser(name));
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<?> createComposition(@PathVariable Long id,
                                               @RequestBody CompositionRequestDto compositionDto) {
        return ResponseEntity.ok(userCompositionService.createComposition(id, compositionDto));
    }

    @PostMapping( "/create")
    public ResponseEntity<?> createCompositionByName(@RequestParam String name,
                                                     @RequestBody CompositionRequestDto compositionRequestDto) {
        return ResponseEntity.ok(userCompositionService.createCompositionByName(name, compositionRequestDto));
    }

    @DeleteMapping("/{id}/{composition_id}")
    public ResponseEntity<?> deleteComposition(@PathVariable Long id, @PathVariable Long composition_id) {
        userCompositionService.deleteCompositionById(id, composition_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCompositionByName(@RequestParam String name, @RequestParam String composition_name) {
        userCompositionService.deleteCompositionByName(name, composition_name);
        return ResponseEntity.noContent().build();
    }
}
