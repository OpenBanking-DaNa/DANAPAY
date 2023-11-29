package com.dana.danapay.common;

public class Pagination {

    // 검색어가 없는 ㅍ ㅔ이징 처리 용도
    public static Criteria getCriteria(int currentPageNo, int totalCount, int limit, int buttonAmount) {

        return getCriteria(currentPageNo, totalCount, limit, buttonAmount, null, null);
    }

    // 검색어가 존재하는 페이징 처리 용도
    public static Criteria getCriteria(int currentPageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue) {
        int maxPageNo;
        int startPageNo;
        int endPageNo;
        int startRow;
        int endRow;

        // 총 페이지수 계산
        maxPageNo = (int) Math.ceil((double) totalCount / limit );

        // 현재 페이지에 보여줄 시작페이지 번호
        startPageNo = (int) (Math.ceil((double) currentPageNo / buttonAmount) -1 ) * buttonAmount + 1;

        // 마지막 페이지번호
        endPageNo = startPageNo + buttonAmount -1;

        // max페이지가 더 작은 경우 마지막 페이지가 max 페이지 번호이다.
        if(maxPageNo < endPageNo){
            endPageNo = maxPageNo;
        }

        // 마지막 페이지는 0이 될수 없기 때문에 게시물이 아무것도 존재하지 않으면 시작페이지번호와 같다.
        if(maxPageNo == 0 && endPageNo == 0) {
            maxPageNo = startPageNo;
            endPageNo = startPageNo;
        }

        // 조회할 시작 번호와 마지막 행 번호를 계산한다.
        startRow = (currentPageNo - 1 ) * limit + 1;
        endRow = (startRow) + limit - 1;

        System.out.println("startRow = " + startRow);
        System.out.println("endRow = " + endRow);

        Criteria selectCriteria = new Criteria(currentPageNo, totalCount, limit, buttonAmount, maxPageNo, startPageNo, endPageNo, startRow, endRow, searchCondition, searchValue);

        return selectCriteria;
    }}
