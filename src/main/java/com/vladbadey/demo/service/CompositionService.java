package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.response.CompositionResponseDto;

import java.util.List;

public interface CompositionService {
    List<CompositionResponseDto> findAllCompositions();

    CompositionResponseDto findComposition(Long id);

    CompositionResponseDto findCompositionByName(String name);

    List<CompositionResponseDto> findAllSortedCompositions();

    List<CompositionResponseDto> findCompositionsByFandom(String name);
}
