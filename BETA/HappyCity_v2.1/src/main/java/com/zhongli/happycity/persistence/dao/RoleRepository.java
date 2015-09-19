package com.zhongli.happycity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhongli.happycity.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
