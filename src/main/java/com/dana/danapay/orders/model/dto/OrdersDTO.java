package com.dana.danapay.orders.model.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private int code;               // 회원코드
    private int orderCode;          // 주문코드
    private Date orderDate ;        // 주문일자
    private int totalQuantity;      // 주문총수량
    private int totalAmount;        // 주문총금액
    private String status;          // 주문상태




}
