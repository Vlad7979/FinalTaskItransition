package com.vladbadey.demo.service;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.dto.response.FandomResponseDto;
import com.vladbadey.demo.entity.Fandom;

import java.util.List;

public interface FandomService {
    List<FandomResponseDto> findAllFandoms();

    void fillUserFandoms(Long id, List<FandomRequestDto> fandoms);

    List<FandomResponseDto> findAllUserFandoms(Long id);

    List<FandomResponseDto> findAllUserFandomsByName(String name);

    void fillUserFandomsByEmail(String email, List<FandomRequestDto> fandoms);

    void updateUserFandomsByName(String name, List<FandomRequestDto> fandoms);
}
