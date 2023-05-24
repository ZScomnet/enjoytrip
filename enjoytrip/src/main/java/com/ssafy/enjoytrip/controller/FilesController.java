package com.ssafy.enjoytrip.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/files")
@CrossOrigin("*")
public class FilesController {
    @PostMapping("/upload")
    public void uploadFile(@RequestPart MultipartFile files, @RequestBody Long user_id){

        String sourceFileName = files.getOriginalFilename();
        String path = "C:/Users/SSAFY/Desktop/pairpjtfinal/enjoy/enjoytrip/enjoytrip/src/main/resources/images";
        Path imagePath = Paths.get(path+sourceFileName);

        try {
            Files.write(imagePath, files.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
