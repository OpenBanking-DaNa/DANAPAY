package com.dana.danapay.review;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class reviewDTO {

    private String reviewCode;      // 리뷰코드
    private String content;         // 리뷰내용
    private Date reviewDate;        // 리뷰일자
    private int reviewStar;         // 별점
    private String reviewReply;     // 리뷰댓글
    private String orderCode;       // 주문코드




}
