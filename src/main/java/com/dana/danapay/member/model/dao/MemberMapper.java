package com.dana.danapay.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /* MONEY-2. 예치금 잔액 조회 */
    int searchMoneyBalance(int code);

}
