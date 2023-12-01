package com.dana.danapay.review.model.service;

import com.dana.danapay.review.model.dao.ReviewMapper;
import com.dana.danapay.review.model.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    /* REVIEW-1. 주문 리뷰 작성 */
    @Transactional
    public boolean writeReview(ReviewDTO reviewDTO) {
        log.info("ReviewService - writeReview---- start");
        try {
            reviewMapper.writeReview(reviewDTO);

            log.info("ReviewService - writeReview---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 ReviewService - writeReview", e);
            throw e;
        }

    }
}
