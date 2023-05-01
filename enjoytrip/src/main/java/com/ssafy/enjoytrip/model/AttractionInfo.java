package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class AttractionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private Long id; // primary key
    @Column(name="content_type_id")
    private Long typeId; // foreign key
    @Column(name="sido_code")
    private int sidoCode; // foreign key
    @Column(name="gugun_code")
    private int gugunCode; // foreign key

    // 여기까지 key 값
    private String title;
    private String addr1;
    private String addr2;
    @Column(name="zipcode")
    private String zipCode;
    private String tel;
    @Column(name="first_image")
    private String firstImage;
    @Column(name="first_image2")
    private String firstImage2;
    @Column(name="readcount")
    private int readCount;
    @Builder.Default
    private BigDecimal latitude;
    @Builder.Default
    private BigDecimal longitude;
    @Column(name="mlevel")
    private String mLevel;
}
