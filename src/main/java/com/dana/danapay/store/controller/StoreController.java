package com.dana.danapay.store.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.store.model.dto.StoreDTO;
import com.dana.danapay.store.model.service.StoreService;
import com.dana.danapay.store.param.StoreAddressOnly;
import com.dana.danapay.store.param.StoreListReq;
import com.dana.danapay.store.param.StorePasswordOnly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;

    public StoreController(StoreService storeService, PasswordEncoder passwordEncoder) {
        this.storeService = storeService;
        this.passwordEncoder = passwordEncoder;
    }

    // 스토어 등록
    @PostMapping
    public ResponseEntity<ResponseDTO> addStore(@RequestBody StoreDTO storeRequest) {

        log.info("store controller ============> storeRequest {}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 등록 결과", storeService.addStore(storeRequest)));
    }

    // 스토어 리스트 조회(페이징)
    @GetMapping
    public ResponseEntity<ResponseDTO> selectStoreList(@ModelAttribute StoreListReq storeRequest) {
        log.info("storeList ============>  storeRequest{}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 조회 결과", storeService.selectStoreList(storeRequest)));

    }

    // 스토어 전체 정보 수정
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @PutMapping("/{sCode}")
    public ResponseEntity<ResponseDTO> wholeUpdateStore(@PathVariable int sCode, @RequestBody StoreDTO storeRequest) {
        storeRequest.setsCode(sCode);
        log.info("wholeUpdateStore ============>  storeRequest{}", storeRequest);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 정보 수정 결과", storeService.wholeUpdateStore(storeRequest)));

    }

    // 스토어 계정 비밀번호 변경
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @PatchMapping("/{sCode}")
    public ResponseEntity<ResponseDTO> anyUpdateStore(@PathVariable int sCode, @RequestBody StoreDTO storeRequest) {
        storeRequest.setsCode(sCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 정보 수정 결과", storeService.anyUpdateStore(storeRequest)));
    }


    // 스토어 삭제
    @PreAuthorize("hasAnyRole('STORE','ADMIN')")
    @DeleteMapping("/{sCode}")
    public ResponseEntity<ResponseDTO> removeStore(@PathVariable int sCode, @RequestBody StoreDTO storeRequest) {
        storeRequest.setsCode(sCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 삭제 결과", storeService.removeStore(storeRequest)));
    }


    
}
