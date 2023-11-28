package com.dana.danapay.money.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.member.model.dao.MemberMapper;
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
    private final MemberMapper memberMapper;

    public MoneyController(MoneyService moneyService, MemberMapper memberMapper) {
        this.moneyService = moneyService;
        this.memberMapper = memberMapper;
    }

    /* MONEY-1. 예치금 내역 조회 @PathVariable 사용 */
    @GetMapping("/search-list/{code}")
    public ResponseEntity<ResponseDTO> searchMoneyList (@PathVariable int code){

        log.info("moneyController : searchMoneyList");
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

        log.info("moneyController : searchMoneyBalance");
        try {
            int result = moneyService.searchMoneyBalance(code);
            log.info("moneyController : result{}", result);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 잔액 조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 잔액 조회 실패", null));
        }
    }

    /* MONEY-3. 예치금 충전 */
    @PostMapping("/charge")
    public ResponseEntity<ResponseDTO> chargeMoney (@RequestBody MoneyDTO moneyDTO){

        log.info("moneyController : chargeMoney");
        try {
            boolean result = moneyService.chargeMoney(moneyDTO);
            log.info("moneyController : result{}", result);
            log.info("chargeMoney balance : {}", moneyService.searchMoneyBalance(moneyDTO.getCode()));
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 충전 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 충전 실패", null));
        }
    }

    /* MONEY-4. 예치금 선물 */
    @PostMapping("/gift")
    public ResponseEntity<ResponseDTO> giftMoney (@RequestBody MoneyDTO moneyDTO){

        log.info("moneyController : giftMoney");
        try {
            int balance = memberMapper.searchMoneyBalance(moneyDTO.getCode());
            int giftAmount = moneyDTO.getMoney();

            if (balance < giftAmount) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "잔액 부족", null));
            }

            boolean result = moneyService.giftMoney(moneyDTO);
            log.info("moneyController : result{}", result);
            log.info("chargeMoney balance : {}", moneyService.searchMoneyBalance(moneyDTO.getCode()));
            log.info("chargeMoney recipient balance : {}", moneyService.searchMoneyBalance(moneyDTO.getRecipientCode()));
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예치금 선물 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "예치금 선물 실패", null));
        }
    }



}
