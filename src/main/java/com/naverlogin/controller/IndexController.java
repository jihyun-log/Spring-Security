package com.naverlogin.controller;

import com.naverlogin.config.auth.PrincipalDetails;
import com.naverlogin.constant.Role;
import com.naverlogin.entity.SocialUser;
import com.naverlogin.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@@AuthenticationPrincipal을 통해서 세션정보에 접근할 수 있음
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("/test/login===================");

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();


        System.out.println("authentication : " + principalDetails.getSocialUser());
        System.out.println("userDetails : " + userDetails.getSocialUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOauthLogin(Authentication authentication,
                                               @AuthenticationPrincipal OAuth2User oauth){
        System.out.println("/test/oauth/login===================");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        System.out.println("authentication : " + oAuth2User.getAttributes());
        System.out.println("oauth2User : " + oauth.getAttributes());

        return "Oauth 세션 정보 확인하기";
    }


    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){

        System.out.println("principalDetails : " + principalDetails.getSocialUser());

        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "login/joinForm";
    }

    @GetMapping("/join")
    public String join(SocialUser socialUser){
        System.out.println(socialUser);
        socialUser.setRole(Role.USER);
        String rawPassword = socialUser.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        socialUser.setPassword(encPassword);
        socialUserRepository.save(socialUser);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터정보";
    }
}