package com.ssafy.enjoytrip.controller;


import com.ssafy.enjoytrip.dto.FilesDto;
//import com.ssafy.enjoytrip.service.FilesService;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RestControllerd
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

//    @Value("${part4.upload.path}") // application.properties의 변수
    private String uploadPath = "C:/Users/gkstj/Desktop/FINALPairPjt/enjoytrip/enjoytrip/src/main/resources/profileimg";


    @PostMapping("/uploadAjax")
    public void uploadFile( MultipartFile uploadFile){

        System.out.println(uploadFile);
            // 이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false){
                // 이미지가 아닌경우 403 Forbidden 반환
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);// 실제 이미지 저장
                System.out.printf("저장 완료");
            }catch (IOException e){
                e.printStackTrace();
            }
        }



    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----
        File uploadPatheFolder = new File(uploadPath,folderPath);

        if(uploadPatheFolder.exists() == false){
            uploadPatheFolder.mkdirs();
        }

        return folderPath;
    }
}
