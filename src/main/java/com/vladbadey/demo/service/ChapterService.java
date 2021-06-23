package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.exceptions.NotFoundException;

public interface ChapterService {

    ChapterResponseDto createChapter(Long id, ChapterRequestDto chapterDto);

    ChapterResponseDto updateChapterById(Long id, ChapterRequestDto chapterDto) throws NotFoundException;

    void deleteChapterById(Long id);
}
