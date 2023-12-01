package com.dana.danapay.store.model.dto;


import com.dana.danapay.auth.model.dto.AuthDTO;
import com.dana.danapay.menu.model.dto.menuDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
public class StoreDTO  {

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

    private List<AuthDTO> authorities;

    public StoreDTO() {
    }

    public StoreDTO(int sCode, String sName, String sIsOpen, String sId, String sPassword, String sPhone, String sAddress, long sBiznumber, double sX, double sY, List<menuDTO> menuList, List<AuthDTO> authorities) {
        this.sCode = sCode;
        this.sName = sName;
        this.sIsOpen = sIsOpen;
        this.sId = sId;
        this.sPassword = sPassword;
        this.sPhone = sPhone;
        this.sAddress = sAddress;
        this.sBiznumber = sBiznumber;
        this.sX = sX;
        this.sY = sY;
        this.menuList = menuList;
        this.authorities = authorities;
    }

    public int getsCode() {
        return sCode;
    }

    public void setsCode(int sCode) {
        this.sCode = sCode;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsIsOpen() {
        return sIsOpen;
    }

    public void setsIsOpen(String sIsOpen) {
        this.sIsOpen = sIsOpen;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public long getsBiznumber() {
        return sBiznumber;
    }

    public void setsBiznumber(long sBiznumber) {
        this.sBiznumber = sBiznumber;
    }

    public double getsX() {
        return sX;
    }

    public void setsX(double sX) {
        this.sX = sX;
    }

    public double getsY() {
        return sY;
    }

    public void setsY(double sY) {
        this.sY = sY;
    }

    public List<menuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<menuDTO> menuList) {
        this.menuList = menuList;
    }

    public List<AuthDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthDTO> authorities) {
        this.authorities = authorities;
    }
}
