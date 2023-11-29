package com.dana.danapay.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /* MONEY-2. 예치금 잔액 조회 */
    int searchMoneyBalance(int code);

    /* MONEY-3. 예치금 충전 - 잔액 반영 */
    void chargeMoney(int code, int money, String option);

    /* MONEY-3. 예치금 선물 - 잔액 반영 */
    void giftMoney(int code, int money, String option, int recipientCode);
}