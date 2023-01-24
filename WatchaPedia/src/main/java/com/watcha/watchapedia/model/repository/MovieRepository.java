package com.watcha.watchapedia.model.repository;


import com.watcha.watchapedia.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByMovIdx(Long movIdx);
}
