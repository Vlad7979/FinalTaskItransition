package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.exceptions.NotFoundException;

import java.util.Set;

public interface ChapterService {

    ChapterResponseDto findChapter(Long chapter_id);

    Set<ChapterResponseDto> findChaptersByCompositionName(String name);

    ChapterResponseDto createChapter(Long id, ChapterRequestDto chapterDto);

    ChapterResponseDto updateChapterById(Long id, ChapterRequestDto chapterDto) throws NotFoundException;

    void deleteChapterById(Long id);

    ChapterResponseDto createChapterByName(String name, ChapterRequestDto chapterDto);
}
