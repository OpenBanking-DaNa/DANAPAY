package com.dana.danapay.member.controller;

import com.dana.danapay.common.ResponseDTO;
import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.member.model.dto.MemberDTO;
import com.dana.danapay.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }


    /* MEMBER-1. 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO) {
        try {
            // 아이디 조회
            MemberDTO member = memberMapper.findById(memberDTO.getId());
            log.info("MemberService - login - member {}", member);

            if(member != null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "이미 존재하는 아이디입니다.", null));
            }

            boolean result = memberService.signup(memberDTO);
            log.info("MemberController : result{}", result);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원가입 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입 실패", null));
        }
    }
}
