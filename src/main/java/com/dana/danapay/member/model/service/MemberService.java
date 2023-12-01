//package com.dana.danapay.member.model.service;
//
//import com.dana.danapay.exception.LoginFailedException;
//import com.dana.danapay.jwt.TokenProvider;
//import com.dana.danapay.member.model.dao.MemberMapper;
//import com.dana.danapay.member.model.dto.MemberDTO;
//import com.dana.danapay.security.dto.TokenDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Slf4j
//public class MemberService {
//
//    private final MemberMapper memberMapper;
//    private final PasswordEncoder passwordEncoder;
//
//    private final TokenProvider tokenProvider;
//
//    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
//        this.memberMapper = memberMapper;
//        this.passwordEncoder = passwordEncoder;
//        this.tokenProvider = tokenProvider;
//    }
//
//    /* MEMBER-1. 회원가입 */
//    @Transactional
//    public boolean signup(MemberDTO memberDTO) {
//        log.info("MemberService - signup---- start");
//        log.info("MemberService : member {}", memberDTO);
//        try{
//            // 평문의 암호문자열을 암호화 시켜서 전달
//            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
//            memberMapper.signup(memberDTO);
//            return true;
//        } catch (Exception e) {
//            log.error("에러발생 moneyService - giftMoney", e);
//            throw e;
//        }
//    }
//
//
//    /* MEMBER-2. 로그인 */
//    public Object login(MemberDTO memberDTO) {
//        log.info("MemberService - login---- start");
//        log.info("MemberService : memberDTO {}", memberDTO);
//        try{
//            // 1. 아이디 조회
//            MemberDTO member = memberMapper.findById(memberDTO.getId());
//            log.info("MemberService - login - member {}", member);
//
//            if(member == null){
//                throw new LoginFailedException(memberDTO.getId() + "를 찾을 수 없습니다.");
//            }
//
//            // 2. 비밀번호 매칭 (passwordEncoder.matches(평문, 다이제스트))
//            if(!passwordEncoder.matches(memberDTO.getPassword(), member.getPassword())){
//                log.info("[AuthService] login - Password Match Fail!");
//                throw new LoginFailedException("잘못된 비밀번호 입니다.");
//            }
//
//            // 3. 토큰 발급
//            TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
//            log.info("[MemberService] login - TokenDTO {} ", tokenDTO);
//
//            log.info("[MemberService] login End ===========================");
//            return tokenDTO;
//        } catch (Exception e) {
//            log.error("에러발생 moneyService - giftMoney", e);
//            throw e;
//        }
//    }
//
//}
