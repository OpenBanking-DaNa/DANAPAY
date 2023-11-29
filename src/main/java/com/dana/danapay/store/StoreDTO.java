package com.dana.danapay.store;


import com.dana.danapay.menu.menuDTO;

import java.util.List;

public class StoreDTO {

    private int sCode;              // 스토어 코드 (시퀀스)
    private String sId;             // 스토어 계정 아이디
    private String sPassword;       // 스토어 계정 비밀번호
    private String sName;           // 스토어 이름
    private String sIsOpen;           // 스토어 오픈 여부

    private String sPhone;          // 스토어 연락처
    private String sAddress;      // 스토어 주소

    private long sBiznumber;     // 사업자 번호

    private double sX;          // 위치 경도
    private double sY;          // 위치 위도

    private List<menuDTO> menuList; // 메뉴리스트

    public StoreDTO() {
    }

    // 메뉴리스트 생성자는 빠짐


    public StoreDTO(int sCode, String sId, String sPassword, String sName, String sIsOpen, String sPhone, String sAddress, long sBiznumber, double sX, double sY, List<menuDTO> menuList) {
        this.sCode = sCode;
        this.sId = sId;
        this.sPassword = sPassword;
        this.sName = sName;
        this.sIsOpen = sIsOpen;
        this.sPhone = sPhone;
        this.sAddress = sAddress;
        this.sBiznumber = sBiznumber;
        this.sX = sX;
        this.sY = sY;
        this.menuList = menuList;
    }

    public int getsCode() {
        return sCode;
    }

    public void setsCode(int sCode) {
        this.sCode = sCode;
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

    public List<menuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<menuDTO> menuList) {
        this.menuList = menuList;
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

    @Override
    public String toString() {
        return "StoreDTO{" +
                "sCode=" + sCode +
                ", sId='" + sId + '\'' +
                ", sPassword='" + sPassword + '\'' +
                ", sName='" + sName + '\'' +
                ", sIsOpen=" + sIsOpen +
                ", sPhone='" + sPhone + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", sBiznumber=" + sBiznumber +
                ", sX=" + sX +
                ", sY=" + sY +
                ", menuList=" + menuList +
                '}';
    }
}
