package com.dana.danapay.store.param;

import com.dana.danapay.store.model.dto.StoreAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePasswordOnly implements StoreAccount {

    private int sCode;
    private String sId;
    private String sPassword;
    private String newPassword;


    @Override
    public int sCode() {
        return this.sCode;
    }

    @Override
    public String sId() {
        return this.sId;
    }

    @Override
    public String sPassword() {
        return this.sPassword;
    }
}
