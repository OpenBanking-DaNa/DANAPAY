package com.dana.danapay.member.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private int code;           // 회원코드
    private String id;          // 회원아이디
    private String password;    // 회원비밀번호
    private String nickname;    // 회원닉네임
    private int balance;        // 예치금잔액


}
