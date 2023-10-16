package com.naverlogin.controller;

import com.naverlogin.constant.Role;
import com.naverlogin.entity.NaverUser;
import com.naverlogin.repository.NaverUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public @ResponseBody String manager(NaverUser naverUser){
        System.out.println(naverUser);
        return "manager";
    }

    @GetMapping("/naverloginForm")
    public String naverloginForm(){
        return "login/naverloginForm";
    }

    @GetMapping("/naverjoinForm")
    public String naverjoinForm(){
        return "login/naverjoinForm";
    }

    @GetMapping("/join")
    public String join(NaverUser naverUser){
        System.out.println(naverUser);
        naverUser.setRole(Role.USER);
        String rawPassword = naverUser.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        naverUser.setPassword(encPassword);
        naverUserRepository.save(naverUser);
        return "redirect:/naverloginForm";
    }

}