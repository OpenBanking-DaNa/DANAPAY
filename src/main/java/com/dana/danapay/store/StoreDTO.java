package com.dana.danapay.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StoreDTO {

    private int sCode;
    private String sId;
    private String sPassword;
    private String sName;
    private char sIsOpen;

}
