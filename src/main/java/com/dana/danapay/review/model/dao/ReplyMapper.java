package com.dana.danapay.review.model.dao;

import com.dana.danapay.common.Criteria;
import com.dana.danapay.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {


    int reviewTotalCount(int sCode);

    List<ReviewDTO> selectStoreReview(Criteria criteria, int sCode);

    int postReply(ReviewDTO reviewRequest);

    void deleteReply(String reviewRequest);
}
