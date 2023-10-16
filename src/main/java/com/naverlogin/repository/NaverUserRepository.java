package com.naverlogin.repository;

import com.naverlogin.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverUserRepository extends JpaRepository<SocialUser, Long> {

    public SocialUser findByName(String name);

}
