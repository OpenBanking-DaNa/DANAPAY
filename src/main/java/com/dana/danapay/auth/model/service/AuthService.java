package com.dana.danapay.auth.model.service;

import com.dana.danapay.auth.model.dao.AuthMapper;
import com.dana.danapay.auth.model.dto.LoginUser;
import com.dana.danapay.exception.PasswordException;
import com.dana.danapay.exception.TokenException;
import com.dana.danapay.security.TokenProvider;
import com.dana.danapay.security.dto.TokenDTO;
import com.dana.danapay.store.model.dto.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public Object memberLogin(LoginUser request) {

        try {
            if(!passwordCheck(request.getId(), request.getPassword(), "member")){
                return "비밀번호가 일치하지 않습니다.";
            }
            request = authMapper.findByMemberId(request.getId());
            TokenDTO token = tokenProvider.generateTokenDTO(request);

            return token;
        } catch(Exception e){
            throw new TokenException("토큰 발급 실패, 다시 시도해주세요.");
        }
    }

    public Object storeLogin(LoginUser request) {
        try {
            if(!passwordCheck(request.getId(), request.getPassword(), "store")){
                return "비밀번호가 일치하지 않습니다.";
            }
            request = authMapper.findByStoreId(request.getId());
            log.info("storeLogin============> request {}", request);

            TokenDTO token = tokenProvider.generateTokenDTO(request);

            return token;
        } catch(Exception e){
            throw new TokenException("토큰 발급 실패, 다시 시도해주세요.");
        }
    }



    // 비밀번호 체크
    private boolean passwordCheck(String id, String password, String type){
        try {
            String encodePassword = "";

            if(type.equals("member")){
                encodePassword = authMapper.getMemberPassword(id);
            } else if(type.equals("store")){
                encodePassword = authMapper.getStorePassword(id);
            }

            if (encodePassword != null && passwordEncoder.matches(password, encodePassword)) {
                // 비밀번호 일치
                return true;
            } else {
                // 비밀번호 불일치
                log.warn("존재하지 않는 아이디 혹은 비밀번호가 틀립니다. Store ID: " + id);
                return false;
            }
        } catch (Exception e) {
            // Log the exception and throw a custom exception
            log.error("비밀번호 확인 중 오류가 발생했습니다. Store ID: " + id, e);
            throw new PasswordException("비밀번호 확인 중 오류가 발생했습니다.");
        }
    }
}
