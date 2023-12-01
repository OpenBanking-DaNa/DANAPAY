package com.dana.danapay.review.model.service;

import com.dana.danapay.review.model.dao.ReviewMapper;
import com.dana.danapay.review.model.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /* REVIEW-2. 작성 리뷰 전체 조회 By 회원코드 */
    public List<ReviewDTO> searchReviewByCode(int code) {
        log.info("ReviewService - searchReviewByCode---- start");
        try {
            List<ReviewDTO> result = reviewMapper.searchReviewByCode(code);
            log.info("ReviewService - searchReviewByCode---- end");
            return result;
        } catch (Exception e) {
            log.error("에러발생 ReviewService - searchReviewByCode", e);
            throw e;
        }
    }

    /* /* REVIEW-3. 주문코드별 리뷰 조회 */
    public List<ReviewDTO> searchReviewByOrderCode(String orderCode) {
        log.info("ReviewService - searchReviewByOrderCode---- start");
        try {
            List<ReviewDTO> result = reviewMapper.searchReviewByOrderCode(orderCode);
            log.info("ReviewService - searchReviewByOrderCode---- end");
            return result;
        } catch (Exception e) {
            log.error("에러발생 ReviewService - searchReviewByOrderCode", e);
            throw e;
        }
    }

//    // 통합조회
//    public List<ReviewDTO> searchReview(int code, String orderCode) {
//        log.info("ReviewService - searchReviewByCode---- start");
//        try {
//
//            List<ReviewDTO> result = reviewMapper.searchReview(code, orderCode);
//
//            log.info("ReviewService - searchReviewByCode---- end");
//            return result;
//        } catch (Exception e) {
//            log.error("에러발생 ReviewService - searchReviewByCode", e);
//            throw e;
//        }
//
//    }
}
