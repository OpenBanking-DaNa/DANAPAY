//package com.dana.danapay.member.controller;
//
//import com.dana.danapay.common.ResponseDTO;
//import com.dana.danapay.member.model.dto.MemberDTO;
//import com.dana.danapay.member.model.service.MemberService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Slf4j
//@RequestMapping("/api/member")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//
//    /* MEMBER-1. 회원가입 */
//    @PostMapping("/signup")
//    public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO) {
//        try {
//            boolean result = memberService.signup(memberDTO);
//            log.info("MemberController : result{}", result);
//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원가입 성공", result));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입 실패", null));
//        }
//    }
//
//    /* MEMBER-2. 로그인 */
//    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){
//        try {
//            Object result = memberService.login(memberDTO);
//            log.info("MemberController : result{}", result);
//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", result));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "로그인 실패", null));
//        }
//    }
//
//}
