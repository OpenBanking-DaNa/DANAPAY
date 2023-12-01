package com.dana.danapay.security.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class TokenDTO {

    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;

    private int code;
    private String name;



}