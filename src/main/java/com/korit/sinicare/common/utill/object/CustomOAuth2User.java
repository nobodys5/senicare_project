package com.korit.sinicare.common.utill.object;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

// 사용자 지정 OAuth2 유저 객체
public class CustomOAuth2User implements OAuth2User {

    private String name;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    // boolean 으로 userid가 존재하는지 아닌지 확인하기 위해 메서드 추가
    private boolean existed;

    public CustomOAuth2User(String name, Map<String, Object> attributes, boolean existed) {
        this.name = name;
        this.attributes = attributes;
        this.authorities = AuthorityUtils.NO_AUTHORITIES;
        this.existed = existed;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
        
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    public boolean isExisted() {
        return this.existed;
    }
}
