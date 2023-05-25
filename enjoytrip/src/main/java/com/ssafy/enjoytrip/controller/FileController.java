package com.ssafy.enjoytrip.controller;


import com.ssafy.enjoytrip.dto.FilesDto;
//import com.ssafy.enjoytrip.service.FilesService;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.service.FileService;
import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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

//    private String uploadPath = "C:/profileimg";
    private String uploadPath = System.getProperty("user.dir")+"/src/main/resources/profileimg/";
    private String uploadPath2 = System.getProperty("user.dir")+"/build/resources/main/profileimg/";
    private final FileService fileService;

    @PostMapping("/uploadFile/{user_id}")
    public ResponseEntity<?> uploadFile(@RequestPart MultipartFile uploadFile, @PathVariable Long user_id){


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
        String saveName = "profileImg"+'_' + user_id + extensionName;
        Path savePath = Paths.get(uploadPath  + saveName);
        Path savePath2 = Paths.get(uploadPath2  + saveName);

        try {
            uploadFile.transferTo(savePath);// 실제 이미지 저장
            uploadFile.transferTo(savePath2);
            fileService.uploadFile(saveName,user_id);
            System.out.printf("저장 완료");
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);


    }

    @GetMapping("/getProfileImg/{username}")
    public ResponseEntity<?> getProfileImg (@PathVariable String username, HttpServletRequest request) throws IOException {
        String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
//        System.out.println("515454654"+path);
        String memberImg = path+fileService.getProfileImg(username);
//        InputStream inputStream = new FileInputStream(memberImg); //accountDto.profileImgPath());
//        byte[] imageByteArray = IOUtils.toByteArray(inputStream);
//        inputStream.close();

//        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
        return new ResponseEntity<>(memberImg, HttpStatus.OK);

    }

}
