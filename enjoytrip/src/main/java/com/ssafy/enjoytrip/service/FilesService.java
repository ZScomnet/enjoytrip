//package com.ssafy.enjoytrip.service;
//
//import com.ssafy.enjoytrip.dto.FilesDto;
//import com.ssafy.enjoytrip.repository.FilesRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.nio.file.Files;
//
//@Service
//@RequiredArgsConstructor
//public class FilesService {
//    private  final FilesRepository filesRepository;
//
//    public void save(FilesDto files) {
//        FilesDto f = new FilesDto();
//        f.setFilename(files.getFilename());
//        f.setFileOriName(files.getFileOriName());
//        f.setFileurl(files.getFileurl());
//
//        filesRepository.save(f);
//    }
//}
