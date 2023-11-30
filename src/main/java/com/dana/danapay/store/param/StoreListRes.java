package com.dana.danapay.store.param;

import com.dana.danapay.menu.menuDTO;

import java.util.List;

public class StoreListRes {

    private int sCode;              // 스토어 코드 (시퀀스)
    private String sName;           // 스토어 이름
    private String sIsOpen;           // 스토어 오픈 여부

    private String sPhone;          // 스토어 연락처
    private String sAddress;      // 스토어 주소

    private long sBiznumber;     // 사업자 번호

    private double sX;          // 위치 경도
    private double sY;          // 위치 위도
    private int distance;       // 거리(m)

    private List<menuDTO> menuList; // 메뉴리스트

    public int getsCode() {
        return sCode;
    }

    public StoreListRes(int sCode, String sName, String sIsOpen, String sPhone, String sAddress, long sBiznumber, double sX, double sY, int distance, List<menuDTO> menuList) {
        this.sCode = sCode;
        this.sName = sName;
        this.sIsOpen = sIsOpen;
        this.sPhone = sPhone;
        this.sAddress = sAddress;
        this.sBiznumber = sBiznumber;
        this.sX = sX;
        this.sY = sY;
        this.distance = distance;
        this.menuList = menuList;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<menuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<menuDTO> menuList) {
        this.menuList = menuList;
    }

    public StoreListRes() {
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
        return "StoreListRes{" +
                "sCode=" + sCode +
                ", sName='" + sName + '\'' +
                ", sIsOpen='" + sIsOpen + '\'' +
                ", sPhone='" + sPhone + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", sBiznumber=" + sBiznumber +
                ", sX=" + sX +
                ", sY=" + sY +
                ", distance=" + distance +
                ", menuList=" + menuList +
                '}';
    }
}
