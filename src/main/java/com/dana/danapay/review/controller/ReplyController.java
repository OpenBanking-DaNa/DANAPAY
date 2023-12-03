package com.dana.danapay.review.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.menu.model.dto.menuDTO;
import com.dana.danapay.review.model.dto.ReviewDTO;
import com.dana.danapay.review.model.service.ReplyService;
import com.dana.danapay.store.param.StoreListReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 스토어 리뷰 댓글 관리
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review/store/{sCode}")
public class ReplyController {

    private final ReplyService replyService;

    // 내 스토어에 달린 리뷰 조회하기
    @GetMapping
    public ResponseEntity<ResponseDTO> storeReview(@PathVariable int sCode, @RequestParam int currentPageNo) {
        log.info("ReplyController storeReview============");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어별 리뷰 조회",
                replyService.selectStoreReview(sCode, currentPageNo)));
    }

    // 내 스토어에 달린 리뷰에 댓글 달기/수정
    @PatchMapping("/{reviewCode}")
    public ResponseEntity<ResponseDTO> postReply(@PathVariable int sCode, @PathVariable String reviewCode,
                                                @RequestBody ReviewDTO reviewRequest) {
        log.info("ReplyController postReply============");
        reviewRequest.setReviewCode(reviewCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "리뷰 댓글 등록",
                replyService.postReply(reviewRequest)));
    }


    // 댓글 삭제하기
    @DeleteMapping("/{reviewCode}")
    public ResponseEntity<ResponseDTO> deleteReply(@PathVariable int sCode, @PathVariable String reviewCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "리뷰 댓글 등록",
                replyService.deleteReply(reviewCode)));
    }

}
