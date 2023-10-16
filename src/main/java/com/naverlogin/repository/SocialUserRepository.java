package com.naverlogin.repository;

import com.naverlogin.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    public SocialUser findByUsername(String username);

}
