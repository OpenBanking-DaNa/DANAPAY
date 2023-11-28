package com.dana.danapay.money.model.service;

import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.money.model.dao.MoneyMapper;
import com.dana.danapay.money.model.dto.MoneyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MoneyService {

    private final MoneyMapper moneyMapper;
    private final MemberMapper memberMapper;

    public MoneyService(MoneyMapper moneyMapper, MemberMapper memberMapper) {
        this.moneyMapper = moneyMapper;
        this.memberMapper = memberMapper;
    }


    /* MONEY-1. 예치금 내역 조회 */
    public List<MoneyDTO> searchMoneyList(int code) {

        log.info("moneyService - searchMoneyList---- start");

        try {
            List<MoneyDTO> moneyList = moneyMapper.searchMoneyByCode(code);
            log.info("moneyService : moneyList : {}", moneyList);
            log.info("moneyService - searchMoneyList---- end");
            return moneyList;
        } catch (Exception e) {
            log.error("에러발생 moneyService - searchMoneyList", e);
            throw e;
        }
    }

    /* MONEY-2. 예치금 잔액 조회 */
    public int searchMoneyBalance(int code) {

        log.info("moneyService - searchMoneyBalance---- start");

        try {
            int balance = memberMapper.searchMoneyBalance(code);
            log.info("moneyService : balance : {}", balance);
            log.info("moneyService - searchMoneyBalance---- end");
            return balance;
        } catch (Exception e) {
            log.error("에러발생 moneyService - searchMoneyBalance", e);
            throw e;
        }
    }
}
