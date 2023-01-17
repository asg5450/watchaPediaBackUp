package com.watcha.watchapedia.repository;

import com.watcha.watchapedia.model.entity.TbAdvertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiseRepository extends JpaRepository<TbAdvertise, Long> {
}
