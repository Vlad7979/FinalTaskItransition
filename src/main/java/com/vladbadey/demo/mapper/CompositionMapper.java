package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Chapter;
import com.vladbadey.demo.entity.Composition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CompositionMapper {

    public abstract CompositionResponseDto toResponseDto(Composition composition);

    @Mapping(target = "id", ignore = true)
    public abstract Composition toEntity(CompositionRequestDto compositionRequestDto);

    @Mapping(target = "id", ignore = true)
    public abstract void updateComposition(CompositionRequestDto compositionRequestDto,
                                           @MappingTarget Composition composition);
}
