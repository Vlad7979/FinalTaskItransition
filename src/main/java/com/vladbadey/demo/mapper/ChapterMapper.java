package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ChapterMapper {

    public abstract ChapterResponseDto toResponseDto(Chapter chapter);

    @Mapping(target = "id", ignore = true)
    public abstract Chapter toEntity(ChapterRequestDto chapterRequestDto);

    @Mapping(target = "id", ignore = true)
    public abstract void updateChapter(ChapterRequestDto chapterDto, @MappingTarget Chapter chapter);
}
