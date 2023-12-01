package com.dana.danapay.review.model.dao;

import com.dana.danapay.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    /* REVIEW-1. 주문 리뷰 작성 */
    void writeReview(ReviewDTO reviewDTO);

//    List<ReviewDTO> searchReview(int code, String orderCode);

    /* REVIEW-2. 작성 리뷰 전체 조회 By 회원코드 */
    List<ReviewDTO> searchReviewByCode(int code);

    /* REVIEW-3. 주문코드별 리뷰 조회 */
    List<ReviewDTO> searchReviewByOrderCode(String orderCode);
}
