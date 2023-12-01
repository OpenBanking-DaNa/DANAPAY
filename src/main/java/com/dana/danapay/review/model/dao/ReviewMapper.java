package com.dana.danapay.review.model.dao;

import com.dana.danapay.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    /* REVIEW-1. 주문 리뷰 작성 */
    void writeReview(ReviewDTO reviewDTO);
}
