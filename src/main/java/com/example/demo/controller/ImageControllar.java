package com.example.demo.controller;

import com.example.demo.entity.dto.ImageDto;
import com.example.demo.excaption.BadRequest;
import com.example.demo.servise.ImageService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")
public class ImageControllar {
    private final ImageService imageService;
    
    public ImageControllar(ImageService imageSearvice) {
        this.imageService = imageSearvice;
    }

    @PostMapping
    public String create (@RequestParam  MultipartFile file){
    return imageService.create(file);
    }

    @GetMapping("/load/{filename:.+}")
    public @ResponseBody ResponseEntity<?> saveFile(@PathVariable String filename){
        UrlResource file = imageService.load(filename);
        return ResponseEntity.ok().header("Content-Disposition",
                "attachment; filename=" + "image.png").body(file);
    }

    @GetMapping(value = "/get/{link:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public
    @ResponseBody
    byte[] getImage(@PathVariable("link") String link) {
        if (link != null && link.length() > 0) {
            try {
                return imageService.getImg(link);
            } catch (Exception e) {
                throw new BadRequest("Image not found");
            }
        }
        return null;
    }
}
