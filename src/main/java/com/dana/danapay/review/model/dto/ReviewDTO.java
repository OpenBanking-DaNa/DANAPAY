package com.dana.danapay.review.model.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private String reviewCode;      // 리뷰코드
    private String content;         // 리뷰내용
    private Date reviewDate;        // 리뷰일자
    private int reviewStar;         // 별점(0~5)
    private String reviewReply;     // 리뷰댓글
    private String orderCode;       // 주문코드




}
