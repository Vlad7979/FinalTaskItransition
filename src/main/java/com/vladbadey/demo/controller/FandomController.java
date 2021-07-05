package com.vladbadey.demo.controller;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.service.FandomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fandoms")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FandomController {

    private final FandomService fandomService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllFandoms() {
        return ResponseEntity.ok(fandomService.findAllFandoms());
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUserFandoms(@RequestParam String name) {
        return ResponseEntity.ok(fandomService.findAllUserFandomsByName(name));
    }

    @PostMapping("/{id}/fill")
    public void fillUserFandoms(@PathVariable Long id, @RequestBody List<FandomRequestDto> fandoms) {
        fandomService.fillUserFandoms(id, fandoms);
    }

    @PostMapping("/update")
    public void updateUserFandoms(@RequestParam String name, @RequestBody List<FandomRequestDto> fandoms) {
        fandomService.updateUserFandomsByName(name, fandoms);
    }

    @PostMapping("/fill")
    public void fillUserFandomsByEmail(@RequestParam String email, @RequestBody List<FandomRequestDto> fandoms) {
        fandomService.fillUserFandomsByEmail(email, fandoms);
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<?> getAllUserFandoms(@PathVariable Long id) {
        return ResponseEntity.ok(fandomService.findAllUserFandoms(id));
    }
}
