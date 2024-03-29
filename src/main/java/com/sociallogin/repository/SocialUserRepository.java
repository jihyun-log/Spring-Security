package com.sociallogin.repository;

import com.sociallogin.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    public SocialUser findByUsername(String username);

    public SocialUser findByEmail(String Email);

}