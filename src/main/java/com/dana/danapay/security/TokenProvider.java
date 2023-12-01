package com.dana.danapay.security;

import com.dana.danapay.auth.model.dto.LoginUser;
import com.dana.danapay.exception.TokenException;
import com.dana.danapay.security.dto.TokenDTO;
import com.dana.danapay.store.model.dto.StoreDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private final String BEARER_TYPE = "Bearer"; // 토큰 타입
    private final String AUTHORITIES_KEY = "auth";   // 권한클레임 key값
    private final Key key;     // access 토큰 전용 시크릿키

    @Value("${jwt.expire-time}")
    public long ACCESS_TOKEN_EXPIRE_TIME;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserDetailsService userDetailsService;

    @Autowired
    public TokenProvider(@Value("${jwt.secret}") String secretAccessKey, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        byte[] AkeyBytes = Decoders.BASE64.decode(secretAccessKey);
        this.key = Keys.hmacShaKeyFor(AkeyBytes);
    }

    // 1. 토큰 생성
    public TokenDTO generateTokenDTO(LoginUser loginUser) {
        log.info("[TokenProvider] generateTokenDTO : loginUser === {}", loginUser);

        // 클레임 설정( + 회원아이디, 권한)
        Claims claims = Jwts.claims().setSubject(loginUser.getId()); // id
        claims.put(AUTHORITIES_KEY, loginUser.getRoleList().stream()
                .map(role -> role.getAuthName()).distinct()
                .collect(Collectors.toList()));

        // 만료시간 설정
        long now = System.currentTimeMillis();
        Date AccessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        log.info("[TokenProvider] generateTokenDTO : ACCESS_TOKEN_EXPIRE_TIME === {}", ACCESS_TOKEN_EXPIRE_TIME);


        // 엑세스 토큰 빌드
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(AccessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        log.info("[TokenProvider] generateTokenDTO : accessToken === {}", accessToken);

        // 응답 토큰객체 빌드 및 리턴
        return  TokenDTO.builder().grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(now + ACCESS_TOKEN_EXPIRE_TIME)
                .code(loginUser.getCode())
                .name(loginUser.getName())
                .build();
    }

    // 토큰 헤더 추출
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        log.info("[TokenProvider] resolveToken : bearetToken === {}",bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            // 전송받은 값에서 'Bearer ' 뒷부분 추출
            return bearerToken.substring(BEARER_TYPE.length()).trim();
        }
        return null;
    }

    // 토큰 유효성 검사
    public boolean validateToken(String jwt) throws ServletException, IOException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException("잘못된 JWT 서명이나 형식으로 검증에 실패하였습니다.");
        } catch (ExpiredJwtException e) {
            throw new TokenException("만료된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("지원되지 않는 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

    // 토큰 클레임 추출
    public Claims parseClaims(String jwt) {
        try {
            // jwts 파서빌더 -> 사인키값 셋팅 -> 빌드 -> 클레임 파싱 -> 바디내용 취득
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        } catch (ExpiredJwtException e) {
            return e.getClaims();   // 토큰이 만료되어 예외가 발생하더라도 클레임 값을 뽑는다.
        }
    }

    // 권한 객체 추출
    public Authentication getAuthentication(String jwt) {

        // 클레임 추출(복호화)
        Claims claims = parseClaims(jwt);
        if(claims.get(AUTHORITIES_KEY) == null){
            throw new TokenException("권한 정보가 없는 토큰입니다.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        log.info("[TokenProvider] getAuthentication : getAuthorities === {}", userDetails.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // access 토큰 아이디 취득
    public String getUserId(String jwt) {
        log.info("[TokenProvider] getUserId : jwt === {}",jwt);

        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
                // subject에 id, auth값 담겨있음
    }

}
