package com.dana.danapay.money.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.money.model.dto.MoneyDTO;
import com.dana.danapay.money.model.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/money")
public class MoneyController {

    private final MoneyService moneyService;

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    // 예치금 조회
    @GetMapping("/search/{code}")
    public ResponseEntity<ResponseDTO> searchMoney (@PathVariable int code){

        log.info("moneyController : searchMoney --- start");
        log.info("moneyController : searchMoney --- code : " + code);

        try {
            List<MoneyDTO> result = moneyService.searchMoney(code);
            log.info("moneyController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 조회 실패", null));
        }

    }
}