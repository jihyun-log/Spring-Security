package com.naverlogin.config.auth;

import com.naverlogin.entity.NaverUser;
import com.naverlogin.repository.NaverUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessingUrl("/login");
//login 요청이 오면 자동으로 UserDetailService 타입으로 ioc되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private NaverUserRepository naverUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        System.out.println("name" + name);

        NaverUser userEntity = naverUserRepository.findByName(name);

        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }


        return null;
    }
}