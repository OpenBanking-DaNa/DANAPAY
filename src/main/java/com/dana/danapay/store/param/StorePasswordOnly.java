package com.dana.danapay.store.param;

import com.dana.danapay.store.model.dto.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePasswordOnly implements Account {

    private int sCode;
    private String sId;
    private String sPassword;
    private String newPassword;


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
