package com.dana.danapay.auth.model.service;

import com.dana.danapay.auth.model.dao.AuthMapper;
import com.dana.danapay.auth.model.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthMapper authMapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String sId) throws UsernameNotFoundException {

        LoginUser loginUser = authMapper.findByStoreId(sId);

        List<GrantedAuthority> authority = new ArrayList<>();
        loginUser.getRoleList().stream().map(role -> role.getAuthName()).distinct()
                        .map(auth -> authority.add(new SimpleGrantedAuthority(auth)))
                                .collect(Collectors.toList());

        loginUser.setGrandAuthorities(authority);
        log.info("[CustomUserDetailsService] loadUserByUsername : loginUser === {}", loginUser);
        return loginUser;
    }
}
