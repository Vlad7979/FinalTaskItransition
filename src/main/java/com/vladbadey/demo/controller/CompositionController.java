package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.service.CompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/compositions")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CompositionController {

    private final CompositionRepository compositionRepository;

    private final CompositionService compositionService;

    @GetMapping("/all")
    public List<Composition> getAllCompositions() {
        return compositionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Composition getComposition(@PathVariable Long id) throws NotFoundException {
        return getAllCompositions().stream()
                .filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Cannot find chapter by this id")
                );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComposition(@RequestBody CompositionRequestDto compositionDto) {
        CompositionResponseDto composition = compositionService.createComposition(compositionDto);

        return ResponseEntity.ok(composition);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComposition(@PathVariable Long id,
                                               @RequestBody CompositionRequestDto compositionDto)
            throws NotFoundException {
        CompositionResponseDto compositionResponseDto = compositionService.updateCompositionById(id, compositionDto);
        return ResponseEntity.ok(compositionResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComposition(@PathVariable Long id) {
        compositionService.deleteCompositionById(id);
        return ResponseEntity.noContent().build();
    }
}