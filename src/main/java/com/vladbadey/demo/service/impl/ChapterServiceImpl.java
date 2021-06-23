package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.entity.Chapter;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.mapper.ChapterMapper;
import com.vladbadey.demo.repository.ChapterRepository;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    private final CompositionRepository compositionRepository;

    private final ChapterMapper chapterMapper;

    @Override
    public ChapterResponseDto createChapter(Long id, ChapterRequestDto chapterDto) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        chapter.setComposition(compositionRepository.findById(id).get());
        Chapter savedChapter = chapterRepository.save(chapter);
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
}
