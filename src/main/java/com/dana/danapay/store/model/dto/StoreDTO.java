package com.dana.danapay.store.model.dto;


import com.dana.danapay.auth.model.dto.AuthDTO;
import com.dana.danapay.menu.menuDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO implements Account {

    private int sCode;              // 스토어 코드 (시퀀스)
    private String sName;           // 스토어 이름
    private String sIsOpen;           // 스토어 오픈 여부

    private String sId;
    private String sPassword;

    private String sPhone;          // 스토어 연락처
    private String sAddress;      // 스토어 주소
    private long sBiznumber;     // 사업자 번호
    private double sX;          // 위치 경도
    private double sY;          // 위치 위도

    private List<menuDTO> menuList; // 메뉴리스트

    private AuthDTO auth;

    @Override
    public int code() {
        return this.sCode;
    }

    @Override
    public String id() {
        return this.sId;
    }

    @Override
    public String password() {
        return this.sPassword;
    }

}
