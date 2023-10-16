package com.naverlogin.repository;

import com.naverlogin.entity.NaverUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverUserRepository extends JpaRepository<NaverUser, Long> {

    public NaverUser findByName(String name);

}
