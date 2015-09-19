package com.zhongli.happycity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhongli.happycity.persistence.model.User;
import com.zhongli.happycity.persistence.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
