package com.watcha.watchapedia.model.repository;


import com.watcha.watchapedia.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
