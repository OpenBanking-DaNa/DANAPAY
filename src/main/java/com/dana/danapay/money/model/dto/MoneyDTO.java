package com.dana.danapay.money.model.dto;

import java.sql.Date;

public class MoneyDTO {

    private String moneyCode;       // 예치금코드
    private String code;            // 회원코드
    private String option;          // 예치금구분
    private int money;              // 예치금금액
    private Date moneyDate;         // 예치금일자


    public MoneyDTO() {
    }

    public MoneyDTO(String moneyCode, String code, String option, int money, Date moneyDate) {
        this.moneyCode = moneyCode;
        this.code = code;
        this.option = option;
        this.money = money;
        this.moneyDate = moneyDate;
    }

    public String getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(Date moneyDate) {
        this.moneyDate = moneyDate;
    }

    @Override
    public String toString() {
        return "moneyDTO{" +
                "moneyCode='" + moneyCode + '\'' +
                ", code='" + code + '\'' +
                ", option='" + option + '\'' +
                ", money=" + money +
                ", moneyDate=" + moneyDate +
                '}';
    }
}