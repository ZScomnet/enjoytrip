package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.Setter;

//@Data
//@Entity
@Getter
@Setter
public class FilesDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fno;

    String filename;
    String fileOriName;
    String fileurl;
}
