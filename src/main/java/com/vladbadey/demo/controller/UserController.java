package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.entity.User;
import com.vladbadey.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUserByEmail(@RequestBody String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }

}