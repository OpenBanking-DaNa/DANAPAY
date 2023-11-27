package com.dana.danapay.member;

import com.dana.danapay.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/member")
public class memberController {

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO> login(@RequestBody memberDTO memberDTO){
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", memberService.login(memberDTO)));
//    }




}
