package com.naverlogin.controller;

import com.naverlogin.constant.Role;
import com.naverlogin.entity.SocialUser;
import com.naverlogin.repository.NaverUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

    @Autowired
    private NaverUserRepository naverUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(SocialUser socialUser){
        System.out.println(socialUser);
        return "manager";
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
        naverUserRepository.save(socialUser);
        return "redirect:/loginForm";
    }

}