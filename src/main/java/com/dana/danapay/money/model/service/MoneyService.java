package com.dana.danapay.money.model.service;

import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.money.model.dao.MoneyMapper;
import com.dana.danapay.money.model.dto.MoneyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        log.info("MoneyMapper - searchMoneyList---- start");
        try {
            List<MoneyDTO> moneyList = moneyMapper.searchMoneyByCode(code);
            log.info("MoneyMapper : moneyList : {}", moneyList);
            log.info("MoneyMapper - searchMoneyList---- end");
            return moneyList;
        } catch (Exception e) {
            log.error("에러발생 MoneyMapper - searchMoneyList", e);
            throw e;
        }
    }

    /* MONEY-2. 예치금 잔액 조회 */
    public int searchMoneyBalance(int code) {

        log.info("MoneyMapper - searchMoneyBalance---- start");
        try {
            int balance = memberMapper.searchMoneyBalance(code);
            log.info("MoneyMapper : balance : {}", balance);
            log.info("MoneyMapper - searchMoneyBalance---- end");
            return balance;
        } catch (Exception e) {
            log.error("에러발생 MoneyMapper - searchMoneyBalance", e);
            throw e;
        }
    }

    /* MONEY-3. 예치금 충전/사용 */
    @Transactional
    public boolean chargeMoney(MoneyDTO moneyDTO) {

        log.info("MoneyMapper - chargeMoney---- start");
        try {
            moneyMapper.chargeMoney(moneyDTO);
            memberMapper.chargeMoney(moneyDTO.getCode(), moneyDTO.getMoney(), moneyDTO.getOption());

            int balance = memberMapper.searchMoneyBalance(moneyDTO.getCode());
            log.info("MoneyMapper : balance : {}", balance);

            log.info("MoneyMapper - chargeMoney---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 MoneyMapper - chargeMoney", e);
            throw e;
        }
    }

    /* MONEY-4. 예치금 선물 */
    @Transactional
    public boolean giftMoney(MoneyDTO moneyDTO) {
        log.info("MoneyMapper - giftMoney---- start");
        try {
            moneyMapper.giftMoney(moneyDTO);
            MoneyDTO recipientMoney = new MoneyDTO();
            recipientMoney.setCode(moneyDTO.getRecipientCode());
            recipientMoney.setOption("선물받기");
            recipientMoney.setMoney(moneyDTO.getMoney());
            moneyMapper.giftMoney(recipientMoney);

            // 잔액 반영
            memberMapper.chargeMoney(moneyDTO.getCode(), moneyDTO.getMoney(), moneyDTO.getOption());
            memberMapper.chargeMoney(recipientMoney.getCode(), recipientMoney.getMoney(), recipientMoney.getOption());

            log.info("MoneyMapper - giftMoney---- end");
            return true;
        } catch (Exception e) {
            log.error("에러발생 MoneyMapper - giftMoney", e);
            throw e;
        }
    }
}
