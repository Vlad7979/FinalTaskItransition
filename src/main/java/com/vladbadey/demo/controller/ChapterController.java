package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.entity.Chapter;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.repository.ChapterRepository;
import com.vladbadey.demo.service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/compositions/chapters")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("/{id}/{chapter_id}")
    public ResponseEntity<?> getChapter(@PathVariable Long id, @PathVariable Long chapter_id) throws NotFoundException {
        return ResponseEntity.ok(chapterService.findChapter(chapter_id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getChaptersByCompositionName(@RequestParam String name) {
        return ResponseEntity.ok(chapterService.findChaptersByCompositionName(name));
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<?> createChapter(@PathVariable Long id, @RequestBody ChapterRequestDto chapterDto) {
        ChapterResponseDto chapter = chapterService.createChapter(id, chapterDto);

        return ResponseEntity.ok(chapter);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChapterByName(@RequestParam String name, @RequestBody ChapterRequestDto chapterDto) {
        return ResponseEntity.ok(chapterService.createChapterByName(name, chapterDto));
    }

    @PatchMapping ("/{id}/{chapter_id}")
    public ResponseEntity<?> updateChapter(@PathVariable Long id, @PathVariable Long chapter_id,
                                           @RequestBody ChapterRequestDto chapterDto) throws NotFoundException {
        ChapterResponseDto chapterResponseDto = chapterService.updateChapterById(chapter_id, chapterDto);
        return ResponseEntity.ok(chapterResponseDto);
    }

    @DeleteMapping("/{id}/{chapter_id}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long id, @PathVariable Long chapter_id) {
        chapterService.deleteChapterById(chapter_id);
        return ResponseEntity.noContent().build();
    }
}
