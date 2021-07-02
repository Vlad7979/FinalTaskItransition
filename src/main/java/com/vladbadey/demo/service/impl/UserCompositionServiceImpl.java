package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.exceptions.NotFoundException;
import com.vladbadey.demo.mapper.CompositionMapper;
import com.vladbadey.demo.repository.CompositionRepository;
import com.vladbadey.demo.repository.FandomRepository;
import com.vladbadey.demo.repository.UserRepository;
import com.vladbadey.demo.service.UserCompositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserCompositionServiceImpl implements UserCompositionService {

    private final CompositionRepository compositionRepository;

    private final CompositionMapper compositionMapper;

    private final UserRepository userRepository;

    private final FandomRepository fandomRepository;

    @Override
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
    public CompositionResponseDto updateCompositionById(Long id, Long composition_id, CompositionRequestDto compositionDto)
            throws NotFoundException {
        Composition updatedComposition = compositionRepository.findById(composition_id).
                orElseThrow(() -> new NotFoundException(String.format("Composition with id = %d doesn't exists", composition_id)));

        userRepository.getById(id).getUsersCompositions().remove(compositionRepository.getById(composition_id));
        userRepository.getById(id).getUsersCompositions().add(compositionRepository.getById(composition_id));

        compositionMapper.updateComposition(compositionDto, updatedComposition);
        compositionRepository.save(updatedComposition);

        return compositionMapper.toResponseDto(updatedComposition);
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
    public CompositionResponseDto findUserComposition(Long id) {
        Composition composition = compositionRepository.getById(id);
        return compositionMapper.toResponseDto(composition);
    }

}
