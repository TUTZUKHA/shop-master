package com.example.demo.controller;

import com.example.demo.entity.create.CreateProfile;
import com.example.demo.servise.ProfilrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
private final ProfilrService profilrService;

    public ProfileController(ProfilrService profilrService) {
        this.profilrService = profilrService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newProfile(@RequestBody @Valid  CreateProfile profile){
        String result = profilrService.createProfile(profile);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verification(@PathVariable("token") String token){
        String result = profilrService.verification(token);
        return ResponseEntity.ok(result);
    }
}
