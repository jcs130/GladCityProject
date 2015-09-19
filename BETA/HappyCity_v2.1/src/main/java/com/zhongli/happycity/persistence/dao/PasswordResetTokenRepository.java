package com.zhongli.happycity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhongli.happycity.persistence.model.PasswordResetToken;
import com.zhongli.happycity.persistence.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}
