package com.dana.danapay.orders.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import com.dana.danapay.orders.model.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    /* ORDERS-1. 선택 메뉴 주문 */
    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> order (@RequestBody OrdersDTO ordersDTO) {
        log.info("OrdersController : order");
        log.info("OrdersController - order : ordersDTO {}", ordersDTO);


        try {
            // 총금액, 총수량
            List<OrderMenuDTO> orderList = ordersDTO.getOrderMenuList();
            log.info("OrdersController - order : orderList {}", orderList);

            int totalAmount = orderList.stream().mapToInt(OrderMenuDTO::getAmount).sum();
                // 스트림 요소로 변환 > 스트림의 각 요소를 정수로 매핑 IntStream으로 생성 > IntStream의 요소 더하기
            log.info("OrdersController - order : totalAmount {}", totalAmount);

            int totalPrice = orderList.stream()
                    .mapToInt(OrderMenuDTO -> OrderMenuDTO.getMenuPrice() * OrderMenuDTO.getAmount()).sum();
            log.info("OrdersController - order : totalPrice{}", totalPrice);

            // 예치금 잔액 < 주문총금액인 경우 주문 중지
            int balance = memberMapper.searchMoneyBalance(ordersDTO.getCode());

            if (balance < totalPrice) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "잔액 부족", null));
            }

            ordersDTO.setTotalAmount(totalAmount);
            ordersDTO.setTotalPrice(totalPrice);
            boolean result = ordersService.order(ordersDTO);
            log.info("moneyController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "주문 실패", null));
        }

    }
}
