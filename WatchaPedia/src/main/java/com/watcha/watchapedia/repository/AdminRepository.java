package com.watcha.watchapedia.repository;

import com.watcha.watchapedia.model.entity.TbAdminUser;
import com.watcha.watchapedia.model.entity.TbAdvertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<TbAdminUser, Long> {
    Optional<TbAdminUser> findByAdminId(String adminId);
    Optional<TbAdminUser> findByAdminNumber(String adminNumber);
}
