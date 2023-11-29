package com.dana.danapay.common;

import lombok.Data;

public class Criteria {

    private int currentPageNo;  // 현재 페이지
    private int totalCount ;     // 전체 게시물 수
    private int limit;          // 한 페이지 당 게시물 수
    private int buttonAmount;   // 한 번에 보여줄 페이지 버튼 갯수
    private int maxPageNo;      // 가장 마지막 페이지
    private int startPageNo;    // 한 번에 보여줄 페이징 버튼의 시작하는 페이지 수
    private int endPageNo;      // 한 번에 보여줄 페이징 버튼의 마지막 페이지 수
    private int startRow;       // DB 조회 시 최신글부터 조회해야 하는 행의 시작 수
    private int endRow;         // DB 조회 시 최신글부터 조회해야 하는 행의 마지막 수
    private String searchCondition; // 검색 조건
    private String searchValue;     // 검색어


    public Criteria() {
    }

    public Criteria(int currentPageNo, int totalCount, int limit, int buttonAmount, int maxPageNo, int startPageNo, int endPageNo, int startRow, int endRow, String searchCondition, String searchValue) {
        this.currentPageNo = currentPageNo;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPageNo = maxPageNo;
        this.startPageNo = startPageNo;
        this.endPageNo = endPageNo;
        this.startRow = startRow;
        this.endRow = endRow;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;

    }

    @Override
    public String toString() {
        return "Criteria{" +
                "currentPageNo=" + currentPageNo +
                ", totalCount=" + totalCount +
                ", limit=" + limit +
                ", buttonAmount=" + buttonAmount +
                ", maxPageNo=" + maxPageNo +
                ", startPageNo=" + startPageNo +
                ", endPageNo=" + endPageNo +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", searchCondition='" + searchCondition + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
