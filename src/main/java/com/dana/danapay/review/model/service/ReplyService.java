package com.dana.danapay.review.model.service;

import com.dana.danapay.common.Criteria;
import com.dana.danapay.common.Pagination;
import com.dana.danapay.review.model.dao.ReplyMapper;
import com.dana.danapay.review.model.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dana.danapay.security.JwtFilter.getUserID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    // 스토어별 리뷰 조회
    public Object selectStoreReview(int sCode, int currentPageNo) {

        try {
            int count = replyMapper.reviewTotalCount(sCode);
            log.info("ReplyService reviewTotalCount============> count {}", count);

            if(count > 0) {
                // 보여줄 게시물 수, 버튼수 설정
                int limit = 2;
                int button = 5;

                Criteria criteria = Pagination.getCriteria(currentPageNo, count, limit, button);

                // 조회
                List<ReviewDTO> reviewList = replyMapper.selectStoreReview(criteria, sCode);
                log.info("ReplyService selectStoreReview============> reviewList {}", reviewList);

                return reviewList;

            } else {
                return "등록된 리뷰가 없습니다.";
            }
        } catch(Exception e){
            log.error("스토어 리뷰 조회 중 오류 발생", e);
            return "스토어 리뷰 조회 중 오류 발생";
        }
    }

    // 댓글 등록 및 수정
    @PreAuthorize("hasAnyRole('STORE')")
    @Transactional
    public Object postReply(ReviewDTO reviewRequest) {

        try {
            if(reviewRequest.getReviewReply().equals("") && reviewRequest.getReviewReply() == null){
                return "댓글의 내용이 비어있습니다.";
            }
            int result = replyMapper.postReply(reviewRequest);

            if(result < 0) {
                return "리뷰 댓글 등록 중 오류 발생";
            }
            return "리뷰 댓글 등록 완료";
        } catch(Exception e){
            log.error("Error update reply. review CODE: {}", reviewRequest.getReviewCode(), e);
            return "리뷰 댓글 등록 중 오류 발생 ";
        }

    }

    // 댓글 삭제(빈값으로 업데이트)
    @PreAuthorize("hasAnyRole('STORE')")
    @Transactional
    public Object deleteReply(String reviewCode) {
        log.info("ReplyService deleteReply============> reviewCode {}", reviewCode);

        try {
            replyMapper.deleteReply(reviewCode);
            return "리뷰 댓글 삭제 성공";

        } catch (Exception e) {
            log.error("Error delete reply. review CODE: {}", reviewCode, e);
            return "리뷰 댓글 삭제 중 오류가 발생했습니다.";
        }
    }

}
