package com.dana.danapay.store.param;

import com.dana.danapay.store.model.dto.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreAddressOnly implements Account {

    private int sCode;              // 스토어 코드 (시퀀스)
    private String sId;
    private String sPassword;
    private String sAddress;      // 스토어 주소
    private double sX;          // 위치 경도
    private double sY;          // 위치 위도

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
