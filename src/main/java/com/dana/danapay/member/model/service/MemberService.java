package com.dana.danapay.member.model.service;

import com.dana.danapay.member.model.dao.MemberMapper;
import com.dana.danapay.member.model.dto.MemberDTO;
import com.dana.danapay.security.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /* MEMBER-1. 회원가입 */
    @Transactional
    public boolean signup(MemberDTO memberDTO) {
        log.info("MemberService - signup---- start");
        log.info("MemberService : member {}", memberDTO);
        try{
            // 평문의 암호문자열을 암호화 시켜서 전달
            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            memberMapper.signup(memberDTO);
            return true;
        } catch (Exception e) {
            log.error("에러발생 MemberService - signup", e);
            throw e;
        }
    }
}
