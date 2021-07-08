package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.repository.FandomRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CompositionMapper {

    public CompositionResponseDto toResponseDto(Composition composition) {
        CompositionResponseDto compositionResponseDto = new CompositionResponseDto();
        compositionResponseDto.setName(composition.getName());
        compositionResponseDto.setDescription(composition.getDescription());
        compositionResponseDto.setImage(composition.getImage());
        compositionResponseDto.setFandom(composition.getFandom().getName());
        return compositionResponseDto;
    }

    public Composition toEntity(CompositionRequestDto compositionRequestDto) {
        Composition composition = new Composition();
        composition.setName(compositionRequestDto.getName());
        composition.setDescription(compositionRequestDto.getDescription());
        composition.setImage(compositionRequestDto.getImage());
        return composition;
    }
}
