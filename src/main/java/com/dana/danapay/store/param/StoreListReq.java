package com.dana.danapay.store.param;

public class StoreListReq {

    private double sX;          // 위치 경도
    private double sY;          // 위치 위도
    private int currentPageNo = 1;  // 현재 페이지
    private String searchCondition; // 검색 조건
    private String searchValue;     // 검색어

    public StoreListReq() {
    }

    public StoreListReq(double sX, double sY, int currentPageNo, String searchCondition, String searchValue) {
        this.sX = sX;
        this.sY = sY;
        this.currentPageNo = currentPageNo;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
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

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public String toString() {
        return "StoreRequest{" +
                "sX=" + sX +
                ", sY=" + sY +
                ", currentPageNo=" + currentPageNo +
                ", searchCondition='" + searchCondition + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
