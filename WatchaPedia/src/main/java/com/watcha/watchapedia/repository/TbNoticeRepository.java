package com.watcha.watchapedia.repository;

import com.watcha.watchapedia.model.entity.TbNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbNoticeRepository extends JpaRepository<TbNotice, Long> {
}
