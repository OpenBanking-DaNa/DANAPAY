package com.dana.danapay.store.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.store.StoreDTO;
import com.dana.danapay.store.model.service.StoreService;
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

    @PostMapping("/regist-new")
    public ResponseEntity<ResponseDTO> storeRegist(@RequestBody StoreDTO storeRequest) {

        log.info("store controller ============> storeRequest {}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 등록 결과", storeService.insertNewStore(storeRequest)));
    }


}
