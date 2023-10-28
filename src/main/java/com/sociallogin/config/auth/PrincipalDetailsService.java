package com.sociallogin.config.auth;

import com.sociallogin.entity.SocialUser;
import com.sociallogin.repository.SocialUserRepository;
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
    private SocialUserRepository socialUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username" + username);

        SocialUser userEntity = socialUserRepository.findByUsername(username);

        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }


        return null;
    }
}
