package com.dana.danapay.money.model.dto;

import lombok.*;

import java.sql.Date;

public class MoneyDTO {

    private int moneyCode;          // 예치금코드
    private int code;               // 회원코드
    private String option;          // 예치금구분
    private int money;              // 예치금금액
    private Date moneyDate;         // 예치금일자

    private int recipientCode;     // 선물수령 회원코드

    public MoneyDTO() {
    }

    public MoneyDTO(int moneyCode, int code, String option, int money, Date moneyDate, int recipientCode) {
        this.moneyCode = moneyCode;
        this.code = code;
        this.option = option;
        this.money = money;
        this.moneyDate = moneyDate;
        this.recipientCode = recipientCode;
    }

    public int getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(int moneyCode) {
        this.moneyCode = moneyCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public int getRecipientCode() {
        return recipientCode;
    }

    public void setRecipientCode(int recipientCode) {
        this.recipientCode = recipientCode;
    }

    @Override
    public String toString() {
        return "MoneyDTO{" +
                "moneyCode=" + moneyCode +
                ", code=" + code +
                ", option='" + option + '\'' +
                ", money=" + money +
                ", moneyDate=" + moneyDate +
                ", recipientCode=" + recipientCode +
                '}';
    }
}
