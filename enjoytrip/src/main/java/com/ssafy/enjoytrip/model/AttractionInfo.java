package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="attraction_info")
public class AttractionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private Long id; // primary key
    @Column(name="content_type_id")
    private Long typeId; // foreign key
    @Column(name="sido_code")
    private String sidoCode; // foreign key
    @Column(name="gugun_code")
    private String gugunCode; // foreign key

    // 여기까지 key 값
    @Column(length=100)
    private String title;
    @Column(length=100)
    private String addr1;
    @Column(length=50)
    private String addr2;
    @Column(name="zipcode", length=50)
    private String zipCode;
    @Column(length=50)
    private String tel;
    @Column(name="first_image", length=200)
    private String firstImage;
    @Column(name="first_image2", length=200)
    private String firstImage2;
    @Column(name="readcount")
    private int readCount;
    private String latitude;
    private String longitude;
    @Column(name="mlevel")
    private String mLevel;
}
