package com.dana.danapay.money;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class moneyDTO {

    private String moneyCode;       // 예치금코드
    private String code;            // 회원코드
    private String option;          // 예치금구분
    private int money;              // 예치금금액
    private Date moneyDate;         // 예치금일자



}
