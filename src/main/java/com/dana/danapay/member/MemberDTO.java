package com.dana.danapay.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDTO {


    private String id;
    private int code;
    private String password;
    private String nickname;
}
