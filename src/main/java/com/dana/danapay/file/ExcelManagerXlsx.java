package com.dana.danapay.file;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


// XLSX(Excel 2007 이상) 셀 내용 해석 및 리스트 반환
public class ExcelManagerXlsx {

    private static ExcelManagerXlsx excelXlsxMng;

    public ExcelManagerXlsx() {
    }

    public static ExcelManagerXlsx getInstance() {  // 싱글톤 패턴 적용
        if (excelXlsxMng == null)
            excelXlsxMng = new ExcelManagerXlsx();
        return excelXlsxMng;
    }

    // 파일 읽기
    public List<HashMap<String, String>> getListXlsxRead(String excel) throws Exception {

        List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
        // 파일이 유효한지 검사
        File file = new File( excel );
        if( !file.exists() || !file.isFile() || !file.canRead() ) {
            throw new IOException( excel );
        }
        // xlsx 파일 이용 시
        XSSFWorkbook wb = new XSSFWorkbook( new FileInputStream(file));
        //xls 파일 이용 시
        //HSSFWorkbook  wb = new HSSFWorkbook ( new FileInputStream(file) );

        int check = 0;

        try {
            // 셀 값 읽기
            for( int i=0; i<1; i++ ) {
                for( Row row : wb.getSheetAt(i) ) {
                    if(check != 0) {

                        HashMap<String, String> hMap = new HashMap<String, String>();
                        String valueStr = "";
                        int cellLength = (int) row.getLastCellNum();
                        for(int j=0; j<cellLength; j++){
                            Cell cell = row.getCell(j);

                            if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                                valueStr = "";
                            }else{
                                // 셀 유형(타입)에 따라 데이터 처리
                                switch(cell.getCellType()){
                                    case Cell.CELL_TYPE_STRING:
                                        valueStr = cell.getStringCellValue();
                                        break;
                                    case Cell.CELL_TYPE_NUMERIC: // 날짜 형식이든 숫자 형식이든 다 CELL_TYPE_NUMERIC으로 인식함.
                                        if(DateUtil.isCellDateFormatted(cell)){ // 날짜 유형의 데이터일 경우,
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                                            String formattedStr = dateFormat.format(cell.getDateCellValue());
                                            valueStr = formattedStr;
                                            break;
                                        }else{ // 순수하게 숫자 데이터일 경우,
                                            Double numericCellValue = cell.getNumericCellValue();
                                            if(Math.floor(numericCellValue) == numericCellValue){ // 소수점 이하를 버린 값이 원래의 값과 같다면,,
                                                valueStr = numericCellValue.intValue() + ""; // int형으로 소수점 이하 버리고 String으로 데이터 담는다.
                                            }else{
                                                valueStr = numericCellValue + "";
                                            }
                                            break;
                                        }
                                    case Cell.CELL_TYPE_BOOLEAN:
                                        valueStr = cell.getBooleanCellValue() + "";
                                        break;
                                }
                            }
                            // 데이터 해시맵 저장
                            hMap.put("cell_"+j ,valueStr);
                        }
                        list.add(hMap);
                    }
                    check++;
                }

            }

        } catch( Exception ex ) {
            ex.printStackTrace();
        }

        return list;
    }

}
