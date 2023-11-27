package com.dana.danapay.menu;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class menuDTO {

    private String menuCode;        // 메뉴코드
    private String menuName;        // 메뉴명
    private int menuPrice;          // 메뉴가격
    private char isOrder;           // 메뉴주문여부
    private int storeCode;          // 업체코드
    private String orderCode;       // 주문코드


}
