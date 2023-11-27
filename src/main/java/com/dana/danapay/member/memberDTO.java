package com.dana.danapay.member;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class memberDTO {

    private int code;           // 회원코드
    private String id;          // 회원아이디
    private String password;    // 회원비밀번호
    private String nickname;    // 회원닉네임


}
