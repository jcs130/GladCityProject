package com.zhongli.happycity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhongli.happycity.persistence.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
