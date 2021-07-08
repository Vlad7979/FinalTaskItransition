package com.vladbadey.demo.controller;

import com.vladbadey.demo.service.CompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/compositions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompositionController {

    private final CompositionService compositionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompositions() {
        return ResponseEntity.ok(compositionService.findAllCompositions());
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getSortedCompositions() {
        return ResponseEntity.ok(compositionService.findAllSortedCompositions());
    }

    @GetMapping("/fandom")
    public ResponseEntity<?> getCompositionsByFandom(@RequestParam String name) {
        return ResponseEntity.ok(compositionService.findCompositionsByFandom(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComposition(@PathVariable Long id) {
        return ResponseEntity.ok(compositionService.findComposition(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCompositionByName(@RequestParam String name) {
        return ResponseEntity.ok(compositionService.findCompositionByName(name));
    }
}