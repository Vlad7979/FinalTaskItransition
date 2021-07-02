package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.dto.response.FandomResponseDto;
import com.vladbadey.demo.entity.Fandom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FandomMapper {

    public abstract FandomResponseDto toResponseDto(Fandom fandom);

    @Mapping(target = "id", ignore = true)
    public abstract Fandom toEntity(FandomRequestDto fandomRequestDto);
}
