package com.ssafy.enjoytrip.controller;


import com.ssafy.enjoytrip.dto.FilesDto;
//import com.ssafy.enjoytrip.service.FilesService;
import com.ssafy.enjoytrip.model.Member;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

//    @Value("${part4.upload.path}") // application.properties의 변수
    private String uploadPath = "C:/Users/SSAFY/Desktop/pairpjtfinal/enjoy/enjoytrip/enjoytrip/src/main/resources/profileimg";


    @PostMapping("/uploadFile/{user_id}")
    public ResponseEntity<?> uploadFile(MultipartFile uploadFile, @PathVariable Long user_id){

        System.out.println(uploadFile);
        // 이미지 파일만 업로드 가능
        if(uploadFile.getContentType().startsWith("image") == false){
            log.error("this file is not image type");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
        String originalName = uploadFile.getOriginalFilename();

        String extensionName = originalName.substring(originalName.lastIndexOf("."));
        //UUID
        String uuid = UUID.randomUUID().toString();

        //저장할 파일 이름
        String saveName = uploadPath + File.separator + File.separator + "profileImg"+'_' + user_id + extensionName;

        Path savePath = Paths.get(saveName);

        try {
            uploadFile.transferTo(savePath);// 실제 이미지 저장

            System.out.printf("저장 완료");
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

//    @GetMapping("/File/{username}")
//    public ResponseEntity<?> getProfileImg (@PathVariable String username) throws FileNotFoundException {
//        String memberImg = fileService.getProfileImg(username);
//        InputStream inputStream = new FileInputStream(memberImg); //accountDto.profileImgPath());
//        byte[] imageByteArray = IOUtils.toByteArray(inputStream);
//        inputStream.close();
//        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
//    }

}
