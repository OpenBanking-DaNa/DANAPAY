package com.dana.danapay.auth.controller;

import com.dana.danapay.auth.model.dto.LoginUser;
import com.dana.danapay.auth.model.service.AuthService;
import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.store.model.dto.Account;
import com.dana.danapay.store.model.dto.StoreDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // member Login
    @PostMapping("/member")
    public ResponseEntity<ResponseDTO> memberLogin(@RequestBody LoginUser request) {
        log.info("memberLogin============> request {}", request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "멤버 로그인", authService.memberLogin(request)));

    }

    // store login
    @PostMapping("/store")
    public ResponseEntity<ResponseDTO> storeLogin(@RequestBody LoginUser request) {
        log.info("storeLogin============> request {}", request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "스토어 로그인", authService.storeLogin(request)));

    }


}
