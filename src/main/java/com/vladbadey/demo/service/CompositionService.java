package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.exceptions.NotFoundException;

public interface CompositionService {
    CompositionResponseDto createComposition(CompositionRequestDto compositionDto);

    CompositionResponseDto updateCompositionById(Long id, CompositionRequestDto compositionDto) throws NotFoundException;

    void deleteCompositionById(Long id);
}
