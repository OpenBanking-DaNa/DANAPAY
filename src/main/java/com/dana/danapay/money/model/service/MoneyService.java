package com.dana.danapay.money.model.service;

import com.dana.danapay.money.model.dao.MoneyDAO;
import com.dana.danapay.money.model.dto.MoneyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MoneyService {

    private final MoneyDAO moneyDAO;

    public MoneyService(MoneyDAO moneyDAO) {
        this.moneyDAO = moneyDAO;
    }

    public List<MoneyDTO> searchMoney(int code) {

        log.info("moneyService : searchMoney---- start");
        log.info("moneyService : searchMoney---- 2222");

        try {
            List<MoneyDTO> moneyList = moneyDAO.searchMoneyByCode(code);
            log.info("moneyService : moneyList size: {}", moneyList.size());
            log.info("moneyService : searchMoney---- end");
            return moneyList;
        } catch (Exception e) {
            log.error("An error occurred in moneyService : searchMoney", e);
            throw e;
        }
    }
}
