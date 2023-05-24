package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.repository.BoardRepository;
import com.ssafy.enjoytrip.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    public void uploadFile(String savePath, Long user_id){
        fileRepository.uploadFile(savePath,user_id);
    }

    public String getProfileImg(String username){
        return fileRepository.getProfileImg(username);
    }


}