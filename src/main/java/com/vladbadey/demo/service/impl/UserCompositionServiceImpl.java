package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.mapper.CompositionMapper;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.repository.FandomRepository;
import com.vladbadey.demo.repository.UserRepository;
import com.vladbadey.demo.service.UserCompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserCompositionServiceImpl implements UserCompositionService {

    private final CompositionRepository compositionRepository;

    private final CompositionMapper compositionMapper;

    private final UserRepository userRepository;

    private final FandomRepository fandomRepository;

    @Override
    @Transactional
    public CompositionResponseDto createComposition(Long id, CompositionRequestDto compositionDto) {
        Composition composition = compositionMapper.toEntity(compositionDto);
        composition.setUser(userRepository.getById(id));
        composition.setDate(new Date());
        composition.setFandom(fandomRepository.findByName(compositionDto.getFandom()));
        Composition savedComposition = compositionRepository.save(composition);
        userRepository.getById(id).getUsersCompositions().add(savedComposition);
        userRepository.save(userRepository.getById(id));
        CompositionResponseDto compositionResponseDto = compositionMapper.toResponseDto(savedComposition);
        compositionResponseDto.setFandom(compositionDto.getFandom());
        return compositionResponseDto;
    }


    @Override
    public void deleteCompositionById(Long id, Long composition_id) {
        userRepository.getById(id).getUsersCompositions().remove(compositionRepository.getById(composition_id));
        compositionRepository.getById(composition_id).setUser(null);
        compositionRepository.deleteById(composition_id);
    }

    @Override
    public List<CompositionResponseDto> findAllUserCompositions(Long id) {
        List<CompositionResponseDto> dtoList = new ArrayList<>();
        User user = userRepository.getById(id);
        for (Composition composition : user.getUsersCompositions()) {
            dtoList.add(compositionMapper.toResponseDto(composition));
        }
        return dtoList;
    }

    @Override
    public List<CompositionResponseDto> findAllUserCompositionsByName(String name) {
        List<CompositionResponseDto> dtoList = new ArrayList<>();
        User user = userRepository.findByUsername(name).get();
        for (Composition composition : user.getUsersCompositions()) {
            dtoList.add(compositionMapper.toResponseDto(composition));
        }
        return dtoList;
    }

    @Override
    public CompositionResponseDto createCompositionByName(String name, CompositionRequestDto compositionRequestDto) {
        Composition composition = compositionMapper.toEntity(compositionRequestDto);
        composition.setUser(userRepository.findByUsername(name).get());
        composition.setDate(new Date());
        composition.setFandom(fandomRepository.findByName(compositionRequestDto.getFandom()));
        Composition savedComposition = compositionRepository.save(composition);
        userRepository.findByUsername(name).get().getUsersCompositions().add(savedComposition);
        userRepository.save(userRepository.findByUsername(name).get());
        CompositionResponseDto compositionResponseDto = compositionMapper.toResponseDto(savedComposition);
        compositionResponseDto.setFandom(compositionRequestDto.getFandom());
        return compositionResponseDto;
    }

    @Override
    @Transactional
    public void deleteCompositionByName(String name, String composition_name) {
        userRepository.findByUsername(name).get().getUsersCompositions().remove(compositionRepository.findByName(composition_name));
        compositionRepository.findByName(composition_name).setUser(null);
        compositionRepository.deleteByName(composition_name);
    }

    @Override
    public String findCompositionUser(String name) {
        Composition composition = compositionRepository.findByName(name);
        return composition.getUser().getUsername();
    }

    @Override
    public CompositionResponseDto findUserComposition(Long id) {
        Composition composition = compositionRepository.getById(id);
        return compositionMapper.toResponseDto(composition);
    }

}
