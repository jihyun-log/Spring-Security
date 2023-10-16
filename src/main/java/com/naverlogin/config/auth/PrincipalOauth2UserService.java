package com.naverlogin.config.auth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //구글로부터 받는 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("userRequest :" + userRequest.getClientRegistration());          //registrationId로 어떤 Oauth로 로그인 했는지 확인 가능
        System.out.println("userRequest :" + userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest :" + super.loadUser(userRequest).getAttributes());


        OAuth2User oAuth2User = super.loadUser(userRequest);
        //회원 가입을 강제로 진행해볼 예정
        return super.loadUser(userRequest);
    }
}