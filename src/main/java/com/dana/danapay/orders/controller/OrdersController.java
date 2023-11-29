package com.dana.danapay.orders.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import com.dana.danapay.orders.model.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final MemberMapper memberMapper;

    public OrdersController(OrdersService ordersService, MemberMapper memberMapper) {
        this.ordersService = ordersService;
        this.memberMapper = memberMapper;
    }


    /* ORDERS-1. 주문 하기 */
    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> order (@RequestBody OrdersDTO ordersDTO) {
        log.info("OrdersController : order");
        try {
            int balance = memberMapper.searchMoneyBalance(ordersDTO.getCode());
            int totalAmount = ordersDTO.getTotalAmount();

            if (balance < totalAmount) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "잔액 부족", null));
            }

//            boolean result = ordersService.order(ordersDTO);
//            log.info("moneyController : result{}", result);

//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", result));
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", 1));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "주문 실패", null));
        }

    }
}
