package com.dana.danapay.orderMenu;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class orderMenuDTO {

    private String orderCode;       // 주문코드
    private String menuOrderCode;   // 주문메뉴코드
    private String menuCode;        // 메뉴코드
    private int amount;             // 주문수량



}
