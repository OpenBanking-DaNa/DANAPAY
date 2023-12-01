package com.dana.danapay.auth.model.dto;


import com.dana.danapay.auth.model.dto.AuthDTO;
import com.dana.danapay.menu.menuDTO;
import com.dana.danapay.store.model.dto.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginStore implements UserDetails {

    private int sCode;              // 스토어 코드 (시퀀스)
    private String sName;           // 스토어 이름
    private String sId;

    private List<AuthDTO> roleList;

    private Collection<GrantedAuthority> grandAuthorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grandAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.sName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
