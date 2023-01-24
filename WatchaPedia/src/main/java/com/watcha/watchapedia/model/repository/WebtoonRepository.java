package com.watcha.watchapedia.model.repository;
import com.watcha.watchapedia.model.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
    Optional<Webtoon> findByWebIdx(Long WebIdx);
}
