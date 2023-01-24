package com.watcha.watchapedia.model.repository;

import com.watcha.watchapedia.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findByCommentUserName(String commentUserName);
////    List<Comment> findByQnaText(String qnaText);
}
