package com.dana.danapay.store.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.service.StoreService;
import com.dana.danapay.store.param.StoreListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 스토어 등록
    @PostMapping("/regist-new")
    public ResponseEntity<ResponseDTO> storeRegist(@RequestBody StoreDTO storeRequest) {

        log.info("store controller ============> storeRequest {}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 등록 결과", storeService.insertNewStore(storeRequest)));
    }

    // 스토어 리스트 조회(페이징)
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> storeList(@ModelAttribute StoreListReq storeRequest) {
        log.info("storeList ============>  storeRequest{}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 조회 결과", storeService.selectStoreList(storeRequest)));

    }

    // 스토어 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<ResponseDTO> storeModify(@RequestBody StoreDTO storeRequest) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 정보 수정 결과", storeService.updateStore(storeRequest)));

    }

    // 스토어 삭제
    @PostMapping("/remove")
    public ResponseEntity<ResponseDTO> storeRemove(@RequestBody StoreDTO storeRequest) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 삭제 결과", storeService.deleteStore(storeRequest)));

    }

    // 스토어 계정 비밀번호 변경
    
}
