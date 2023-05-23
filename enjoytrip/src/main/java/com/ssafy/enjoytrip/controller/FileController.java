//package com.ssafy.enjoytrip.controller;
//
//
//import com.ssafy.enjoytrip.dto.FilesDto;
//import com.ssafy.enjoytrip.service.FilesService;
//import jakarta.mail.Multipart;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//
//@CrossOrigin("*")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/file")
//public class FileController {
//
//    private final FilesService filesService;
//    @PostMapping("/upload")
//    public void uploadFile(@RequestPart MultipartFile files){
//        FilesDto file = new FilesDto();
//
//        String sourceFileName = files.getOriginalFilename();
//        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//        File destinationFile;
//        String destinationFileName;
//        String fileUrl = "D:/mung-1/src/main/resources/static/images/";
//        // mung-1은 자기 프로젝트이름으로 체인지!!
//
//        do {
//            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
//            destinationFile = new File(fileUrl + destinationFileName);
//        } while (destinationFile.exists());
//
//        destinationFile.getParentFile().mkdirs();
//        files.transferTo(destinationFile);
//
//        file.setFilename(destinationFileName);
//        file.setFileOriName(sourceFileName);
//        file.setFileurl(fileUrl);
//        filesService.save(file);
//        return "redirect:/mung/insert";
//
//
//    }
//}
