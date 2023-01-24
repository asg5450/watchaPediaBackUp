package com.watcha.watchapedia.model.repository;

import com.watcha.watchapedia.model.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findByQnaUserid(String qnaUserid);
    List<Qna> findByQnaText(String qnaText);


}
