package com.dana.danapay.orders.model.dto;

import com.dana.danapay.orderMenu.model.dto.OrderMenuDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private String orderCode;       // 주문코드
    private int code;               // 회원코드
    private Date orderDate ;        // 주문일자
    private int totalPrice;         // 주문총금액
    private int totalAmount;        // 주문총수량
    private String status;          // 주문상태
    private int sCode;              // 스토어코드

    private List<OrderMenuDTO> orderMenuList;

    public OrdersDTO(String orderCode, int code, Date orderDate, int totalPrice, int totalAmount, String status) {
        this.orderCode = orderCode;
        this.code = code;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
