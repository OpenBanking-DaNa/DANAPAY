package com.dana.danapay.member.model.dao;

import com.dana.danapay.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /* MEMBER-1. 회원가입 */
    void signup(MemberDTO member);

    /* MEMBER-2. 로그인 - 아이디 조회*/
    MemberDTO findById(String id);


    /* MONEY-2. 예치금 잔액 조회 */
    int searchMoneyBalance(int code);

    /* MONEY-3. 예치금 충전/사용 - 잔액 반영 */
    void chargeMoney(int code, int money, String option);

}
