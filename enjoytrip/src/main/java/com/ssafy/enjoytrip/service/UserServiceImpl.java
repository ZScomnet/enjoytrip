package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.getAllUser();
    }

}
