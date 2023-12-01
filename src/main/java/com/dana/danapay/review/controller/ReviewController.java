package com.dana.danapay.review.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.orders.model.dao.OrdersMapper;
import com.dana.danapay.review.model.dto.ReviewDTO;
import com.dana.danapay.review.model.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/review")
public class ReviewController {

    private final OrdersMapper ordersMapper;
    private final ReviewService reviewService;

    public ReviewController(OrdersMapper ordersMapper, ReviewService reviewService) {
        this.ordersMapper = ordersMapper;
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

            boolean result = reviewService.writeReview(reviewDTO);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰작성 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰작성 실패", null));
        }

    }

}
