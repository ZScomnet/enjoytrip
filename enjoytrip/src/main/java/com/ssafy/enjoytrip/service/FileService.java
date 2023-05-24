package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;

import java.util.List;

public interface FileService {

    public void uploadFile(String savePath, Long user_id);

    public String getProfileImg(String username);


}