package com.watcha.watchapedia.model.repository;

import com.watcha.watchapedia.model.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findByQnaUserid(String qnaUserid);
    List<Qna> findByQnaText(String qnaText);


}
