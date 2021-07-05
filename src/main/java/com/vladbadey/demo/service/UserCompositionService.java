package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.exceptions.NotFoundException;

import java.util.List;

public interface UserCompositionService {
    CompositionResponseDto createComposition(Long id, CompositionRequestDto compositionDto);

    CompositionResponseDto updateCompositionById(Long id, Long composition_id, CompositionRequestDto compositionDto) throws NotFoundException;

    void deleteCompositionById(Long id, Long composition_id);

    List<CompositionResponseDto> findAllUserCompositions(Long id);

    CompositionResponseDto findUserComposition(Long id);

    List<CompositionResponseDto> findAllUserCompositionsByName(String name);
}
