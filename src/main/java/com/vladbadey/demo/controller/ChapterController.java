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
@RequestMapping("/api/compositions")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ChapterController {

    private final ChapterRepository chapterRepository;

    private final ChapterService chapterService;

    @GetMapping("/{id}/all")
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    @GetMapping("/{id}/{chapter_id}")
    public Chapter getChapter(@PathVariable Long id, @PathVariable Long chapter_id) throws NotFoundException {
        return getAllChapters().stream()
                .filter(t -> chapter_id.equals(t.getId()))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Cannot find chapter by this id")
                );
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<?> createChapter(@PathVariable Long id, @RequestBody ChapterRequestDto chapterDto) {
        ChapterResponseDto chapter = chapterService.createChapter(id, chapterDto);

        return ResponseEntity.ok(chapter);
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
