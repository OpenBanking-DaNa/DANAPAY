package com.dana.danapay.file;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 엑셀파일 처리 매니저
public class ExcelRequestManager {

    private static ExcelManagerXlsx excelXlsxMng;

    public List<HashMap<String, String>> parseExcelMultiPart(Map<String, MultipartFile> files , String KeyStr,
                                                             int fileKeyParam, String atchFileId , String storePath) throws Exception {
        // 결과를 저장할 리스트
        List<HashMap<String, String>> list = null;

        // 파일의 키 초기화
        int fileKey = fileKeyParam;

        // 저장 경로 및 첨부 파일 아이디 초기화
        String storePathString = "";
        String atchFileIdString = "";

        // 저장 경로가 비어있거나 Null 이면 기본 경로로 저장
        if ("".equals(storePath) || storePath == null) {
            storePathString = "/Users/dhh/Documents/dev/teamproject/DANA/upload/";
        } else {
            storePathString = "/Users/dhh/Documents/dev/teamproject/DANA/upload/"+storePath;
        }

        // 첨부파일 아이디가 비어 있지 않으면 설정
        if (!"".equals(atchFileId) || atchFileId != null) {
            atchFileIdString = atchFileId;
        }

        // 저장 폴더 생성
        File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

        if (!saveFolder.exists() || saveFolder.isFile()) {
            saveFolder.mkdirs();
        }

        // 반복하면서 파일 처리
        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        String filePath = "";

        while (itr.hasNext()) {
            Map.Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            // 원본 파일명 확인
            String orginFileName = file.getOriginalFilename();

            // 웝본 파일명이 비어 있으면 다음 파일로 넘어감
            if ("".equals(orginFileName)) {
                continue;
            }

            // 파일 확장자 추출
            int index = orginFileName.lastIndexOf(".");
            String fileExt = orginFileName.substring(index + 1);
            // 새로운 파일명 생성
            String newName = KeyStr + getTimeStamp() + fileKey;

            // 원본파일명이 비어있지 않으면 업로드된 파일이 존재
            if (!"".equals(orginFileName)) {
                // 파일 경로 설정 및 저장
                filePath = storePathString + File.separator + newName+"."+fileExt;
                // 업로드 된 파일을 새로운 경로에 저장(파일 경로에 대한 검증 수행)
                file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
            }
            // 엑셀 파일 해석 및 결과 리스트에 추가
            list = excelXlsxMng.getInstance().getListXlsxRead(filePath);

            // 파일의 키 증가 처리
            fileKey++;
        }
        // 최종 결과 반환
        return list;
    }

    private String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

}




