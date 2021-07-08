package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.entity.Chapter;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.mapper.ChapterMapper;
import com.vladbadey.demo.repository.ChapterRepository;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.service.ChapterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    private final CompositionRepository compositionRepository;

    private final ChapterMapper chapterMapper;

    @Override
    public ChapterResponseDto findChapter(Long chapter_id) {
        Chapter chapter = chapterRepository.getById(chapter_id);
        return chapterMapper.toResponseDto(chapter);
    }

    @Override
    public Set<ChapterResponseDto> findChaptersByCompositionName(String name) {
        Composition composition = compositionRepository.findByName(name);
        Set<ChapterResponseDto> chapterResponseDtos = new HashSet<>();
        for (Chapter c : composition.getChapters()) {
            chapterResponseDtos.add(chapterMapper.toResponseDto(c));
        }
        return chapterResponseDtos;
    }

    @Override
    @Transactional
    public ChapterResponseDto createChapter(Long id, ChapterRequestDto chapterDto) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        Composition composition = compositionRepository.getById(id);
        chapter.setComposition(composition);
        Chapter savedChapter = chapterRepository.save(chapter);
        composition.getChapters().add(chapter);
        compositionRepository.save(composition);
        return chapterMapper.toResponseDto(savedChapter);
    }

    @Override
    public ChapterResponseDto updateChapterById(Long id, ChapterRequestDto chapterDto) throws NotFoundException {
        Chapter updatedChapter = chapterRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format("Chapter with id = %d doesn't exists", id)));

        chapterMapper.updateChapter(chapterDto, updatedChapter);

        return chapterMapper.toResponseDto(updatedChapter);
    }

    @Override
    public void deleteChapterById(Long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public ChapterResponseDto createChapterByName(String name, ChapterRequestDto chapterDto) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        Composition composition = compositionRepository.findByName(name);
        chapter.setComposition(composition);
        Chapter savedChapter = chapterRepository.save(chapter);
        composition.getChapters().add(chapter);
        compositionRepository.save(composition);
        return chapterMapper.toResponseDto(savedChapter);
    }
}