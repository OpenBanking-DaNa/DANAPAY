package com.dana.danapay.money.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.money.model.dto.MoneyDTO;
import com.dana.danapay.money.model.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Money controller.
 */
@RestController
@Slf4j
@RequestMapping("/api/money")
public class MoneyController {

    private final MoneyService moneyService;

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    /* MONEY-1. 예치금 내역 조회 @PathVariable 사용 */
    @GetMapping("/search-list/{code}")
    public ResponseEntity<ResponseDTO> searchMoneyList (@PathVariable int code){

        log.info("moneyController : searchMoneyList --- start");

        try {
            List<MoneyDTO> result = moneyService.searchMoneyList(code);
            log.info("moneyController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 조회 실패", null));
        }

    }

    /* MONEY-2. 예치금 잔액 조회 @RequestParam 사용 */
    @GetMapping("/search-bs")
    public ResponseEntity<ResponseDTO> searchMoneyBalance (@RequestParam("code") int code){

        log.info("moneyController : searchMoneyBalance --- start");
        log.info("moneyController : searchMoneyBalance --- code : " + code);

        try {
            int result = moneyService.searchMoneyBalance(code);
            log.info("moneyController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 잔액 조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 잔액 조회 실패", null));
        }

    }


}
