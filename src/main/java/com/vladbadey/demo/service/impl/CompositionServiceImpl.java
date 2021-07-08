package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.mapper.CompositionMapper;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.service.CompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompositionServiceImpl implements CompositionService {

    private final CompositionRepository compositionRepository;

    private final CompositionMapper compositionMapper;

    @Override
    public List<CompositionResponseDto> findAllCompositions() {
        List<CompositionResponseDto> dtoList = new ArrayList<>();
        for (Composition composition : compositionRepository.findAll()) {
            dtoList.add(compositionMapper.toResponseDto(composition));
        }
        Collections.shuffle(dtoList);
        return dtoList;
    }

    @Override
    public CompositionResponseDto findComposition(Long id) {
        Composition composition = compositionRepository.getById(id);
        return compositionMapper.toResponseDto(composition);
    }

    @Override
    public CompositionResponseDto findCompositionByName(String name) {
        Composition composition = compositionRepository.findByName(name);
        return compositionMapper.toResponseDto(composition);
    }

    @Override
    public List<CompositionResponseDto> findAllSortedCompositions() {
        List<Composition> compositions = compositionRepository.findAll();
        Collections.sort(compositions, new Comparator<Composition>() {
            @Override
            public int compare(Composition o1, Composition o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        List<CompositionResponseDto> sortedList = new ArrayList<>();
        for (Composition composition : compositions) {
            sortedList.add(compositionMapper.toResponseDto(composition));
        }
        return sortedList;
    }

    @Override
    public List<CompositionResponseDto> findCompositionsByFandom(String name) {
        List<CompositionResponseDto> list = new ArrayList<>();
        for (Composition c : compositionRepository.findAll()) {
            if (c.getFandom().getName().equals(name)) {
                CompositionResponseDto compositionResponseDto = compositionMapper.toResponseDto(c);
                compositionResponseDto.setFandom(c.getFandom().getName());
                list.add(compositionResponseDto);
            }
        }
        return list;
    }
}
