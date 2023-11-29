package com.dana.danapay.menu;

import lombok.*;


public class menuDTO {

    private String menuCode;        // 메뉴코드
    private String menuName;        // 메뉴명
    private int menuPrice;          // 메뉴가격
    private String isOrder;           // 메뉴주문여부
    private int sCode;          // 업체코드

    public menuDTO() {
    }

    public menuDTO(String menuCode, String menuName, int menuPrice, String isOrder, int sCode ) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.isOrder = isOrder;
        this.sCode = sCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public int getSCode() {
        return sCode;
    }

    public void setSCode(int sCode) {
        this.sCode = sCode;
    }

    @Override
    public String toString() {
        return "menuDTO{" +
                "menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", isOrder=" + isOrder +
                ", sCode=" + sCode +
                '}';
    }
}
