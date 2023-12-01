package com.dana.danapay.review.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.orders.model.dao.OrdersMapper;
import com.dana.danapay.review.model.dao.ReviewMapper;
import com.dana.danapay.review.model.dto.ReviewDTO;
import com.dana.danapay.review.model.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
@Slf4j
@RequestMapping("/api/review")
public class ReviewController {

    private final OrdersMapper ordersMapper;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    public ReviewController(OrdersMapper ordersMapper, ReviewMapper reviewMapper, ReviewService reviewService) {
        this.ordersMapper = ordersMapper;
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
    }

    /* REVIEW-1. 주문 리뷰 작성 */
    @PostMapping("/write")
    public ResponseEntity<ResponseDTO> writeReview (@RequestBody ReviewDTO reviewDTO) {
        log.info("ReviewController : writeReview");
        log.info("ReviewController - writeReview : reviewDTO {}", reviewDTO);
        try {

            String status = ordersMapper.searchStatusOrders(reviewDTO.getOrderCode());
            log.info("ReviewController - writeReview : status {}", status);

            if(!"픽업완료".equals(status)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "완료되지 않은 주문입니다.", null));
            }

            List<ReviewDTO> reviewList = reviewMapper.searchReviewByOrderCode(reviewDTO.getOrderCode());
            if(reviewList.size() > 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "이미 리뷰가 작성되었습니다.", null));
            }

            boolean result = reviewService.writeReview(reviewDTO);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰작성 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰작성 실패", null));
        }
    }

    /* REVIEW-2. 작성 리뷰 전체 조회 @RequestParam 사용 */
    @GetMapping("search-code")
    public ResponseEntity<ResponseDTO> searchReviewByCode (@RequestParam(name = "code") int code) {
        log.info("ReviewController : searchReviewByCode");
        log.info("ReviewController - searchReviewByCode : code {}", code);

        try {
            List<ReviewDTO> result = reviewService.searchReviewByCode(code);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰조회 실패", null));
        }
    }

    /* REVIEW-3. 주문코드별 리뷰 조회 @RequestParam 사용 */
    @GetMapping("search-orderCode")
    public ResponseEntity<ResponseDTO> searchReviewByOrderCode (@RequestParam(name = "orderCode") String orderCode) {
        log.info("ReviewController : searchReviewByOrderCode");
        log.info("ReviewController - searchReviewByOrderCode : code {}", orderCode);

        try {

            List<ReviewDTO> result = reviewService.searchReviewByOrderCode(orderCode);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰조회 실패", null));
        }
    }


}
