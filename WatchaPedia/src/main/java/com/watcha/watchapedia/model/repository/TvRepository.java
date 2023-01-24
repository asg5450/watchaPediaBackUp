package com.watcha.watchapedia.model.repository;

import com.watcha.watchapedia.model.entity.Tv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TvRepository extends JpaRepository<Tv, Long> {
    Optional<Tv> findByTvIdx(Long TvIdx);
}
