package com.naverlogin.config.auth;

import com.naverlogin.entity.SocialUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private SocialUser socialUser;
    private Map<String, Object> attributes;

    //일반 로그인
    public PrincipalDetails(SocialUser socialUser) {
        this.socialUser = socialUser;
    }

    //Oauth 로그인
    public PrincipalDetails(SocialUser socialUser, Map<String, Object> attributes){
        this.socialUser = socialUser;
        this.attributes = attributes;
    }

    //해당 socialUser의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collet = new ArrayList<>();
        collet.add(()->{ return socialUser.getRole().toString();});
        return collet;

    }

    @Override
    public String getPassword() {
        return socialUser.getPassword();
    }

    @Override
    public String getUsername() {
        return socialUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        //우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로 함.
        //현재시간 - 로그인시간 => 1년초과하면 return false;

        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }

}