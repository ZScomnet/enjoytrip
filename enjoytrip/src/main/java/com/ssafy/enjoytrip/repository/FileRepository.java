package com.ssafy.enjoytrip.repository;


public interface FileRepository {
    public void uploadFile(String savePath, Long user_id);

    public String getProfileImg(String username);


}