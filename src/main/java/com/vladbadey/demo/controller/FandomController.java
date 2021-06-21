package com.vladbadey.demo.controller;

import com.vladbadey.demo.entity.Fandom;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.repository.FandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/onboarding")
public class FandomController {

    @Autowired
    FandomRepository fandomRepository;

    @GetMapping("/all")
    public List<Fandom> getAllFandoms(User user) {
        return fandomRepository.findAll();
    }
}
