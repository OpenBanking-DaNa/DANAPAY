package com.dana.danapay.orderMenu.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuDTO {

    private String orderCode;       // 주문코드
    private String menuOrderCode;   // 주문메뉴코드
    private String menuCode;        // 메뉴코드
    private int amount;             // 주문수량



}
