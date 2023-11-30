package com.dana.danapay.orders.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import com.dana.danapay.orders.model.dao.OrdersMapper;
import com.dana.danapay.orders.model.dto.OrdersDTO;
import com.dana.danapay.orders.model.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final MemberMapper memberMapper;
    private final OrdersMapper ordersMapper;

    public OrdersController(OrdersService ordersService, MemberMapper memberMapper, OrdersMapper ordersMapper) {
        this.ordersService = ordersService;
        this.memberMapper = memberMapper;
        this.ordersMapper = ordersMapper;
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
            log.info("OrdersController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "주문 실패", null));
        }
    }

    /* ORDERS-2. 주문상태 변경 */
    @PutMapping("/process")
    public ResponseEntity<ResponseDTO> orderProcess (@RequestBody OrdersDTO ordersDTO) {
        log.info("OrdersController : orderProcess");
        log.info("OrdersController - orderProcess : ordersDTO {}", ordersDTO);

        try {

            String updateStatus = ordersDTO.getStatus();

            if(updateStatus == "픽업완료"){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "완료된 주문입니다.", null));
            }

            int searchSCode = ordersMapper.searchSCode(ordersDTO.getOrderCode());
            log.info("OrdersController - orderProcess : searchSCode {}", searchSCode);

            if(searchSCode != ordersDTO.getSCode()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "주문상태 변경 불가 - 업체 불일치", null));
            }

            boolean result = ordersService.orderProcess(ordersDTO.getOrderCode(), updateStatus);
            log.info("OrdersController : result{}", result);

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문상태 변경 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "주문상태 변경 실패", null));
        }
    }


}
