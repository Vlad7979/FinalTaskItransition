package com.vladbadey.demo.service.impl;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.dto.response.FandomResponseDto;
import com.vladbadey.demo.entity.Fandom;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.mapper.FandomMapper;
import com.vladbadey.demo.repository.FandomRepository;
import com.vladbadey.demo.repository.UserRepository;
import com.vladbadey.demo.service.FandomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FandomServiceImpl implements FandomService {

    private final FandomRepository fandomRepository;

    private final UserRepository userRepository;

    private final FandomMapper fandomMapper;

    @Override
    public List<FandomResponseDto> findAllFandoms() {
        List<FandomResponseDto> dtoList = new ArrayList<>();
        for (Fandom fandom : fandomRepository.findAll()) {
            dtoList.add(fandomMapper.toResponseDto(fandom));
        }
        return dtoList;
    }

    @Override
    @Transactional
    public void fillUserFandoms(Long id, List<FandomRequestDto> fandoms) {
        User user = userRepository.getById(id);
        Set<Fandom> fandomList = new HashSet<>();
        for (FandomRequestDto fandomRequestDto : fandoms) {
            fandomList.add(fandomRepository.findByName(fandomRequestDto.getName()));
        }
        user.setFandoms(fandomList);
        userRepository.save(user);
     }

    @Override
    public List<FandomResponseDto> findAllUserFandoms(Long id) {
        User user = userRepository.getById(id);
        List<FandomResponseDto> list = new ArrayList<>();
        for (Fandom fandom : user.getFandoms()) {
            list.add(fandomMapper.toResponseDto(fandom));
        }
        return list;
    }

    @Override
    public List<FandomResponseDto> findAllUserFandomsByName(String name) {
        User user = userRepository.findByUsername(name).get();
        List<FandomResponseDto> list = new ArrayList<>();
        for (Fandom fandom : user.getFandoms()) {
            list.add(fandomMapper.toResponseDto(fandom));
        }
        return list;
    }

    @Override
    @Transactional
    public void fillUserFandomsByEmail(String email, List<FandomRequestDto> fandoms) {
        User user = userRepository.findByEmail(email).get();
        Set<Fandom> fandomList = new HashSet<>();
        for (FandomRequestDto fandomRequestDto : fandoms) {
            fandomList.add(fandomRepository.findByName(fandomRequestDto.getName()));
        }
        user.setFandoms(fandomList);
        userRepository.save(user);
    }

    @Override
    public void updateUserFandomsByName(String name, List<FandomRequestDto> fandoms) {
        User user = userRepository.findByUsername(name).get();
        Set<Fandom> fandomList = user.getFandoms();
        fandomList.clear();
        user.setFandoms(fandomList);
        Set<Fandom> newFandomList = new HashSet<>();
        userRepository.save(user);
        for (FandomRequestDto fandomRequestDto : fandoms) {
            newFandomList.add(fandomRepository.findByName(fandomRequestDto.getName()));
        }
        user.setFandoms(newFandomList);
        userRepository.save(user);
    }
}
