package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Chapter;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.mapper.CompositionMapper;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.service.CompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class CompositionServiceImpl implements CompositionService {
    private final CompositionRepository compositionRepository;

    private final CompositionMapper compositionMapper;

    @Override
    public CompositionResponseDto createComposition(CompositionRequestDto compositionDto) {
        Composition composition = compositionMapper.toEntity(compositionDto);
        Composition savedChapter = compositionRepository.save(composition);
        return compositionMapper.toResponseDto(savedChapter);
    }

    @Override
    public CompositionResponseDto updateCompositionById(Long id, CompositionRequestDto compositionDto)
            throws NotFoundException {
        Composition updatedComposition = compositionRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format("Composition with id = %d doesn't exists", id)));

        compositionMapper.updateComposition(compositionDto, updatedComposition);

        return compositionMapper.toResponseDto(updatedComposition);
    }

    @Override
    public void deleteCompositionById(Long id) {
        compositionRepository.deleteById(id);
    }
}
