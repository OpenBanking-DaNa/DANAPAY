package com.dana.danapay.auth.model.service;

import com.dana.danapay.auth.model.dto.LoginStore;
import com.dana.danapay.store.model.dao.StoreMapper;
import com.dana.danapay.store.model.dto.StoreDTO;
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

    private final StoreMapper storeMapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String sId) throws UsernameNotFoundException {

        LoginStore loginStore = storeMapper.findByStoreId(sId);

        List<GrantedAuthority> authority = new ArrayList<>();
        loginStore.getRoleList().stream().map(role -> role.getAuthName()).distinct()
                        .map(auth -> authority.add(new SimpleGrantedAuthority(auth)))
                                .collect(Collectors.toList());

        loginStore.setGrandAuthorities(authority);
        return loginStore;
    }
}
