package com.watcha.watchapedia.model.dto;

import com.watcha.watchapedia.model.entity.Comment;
import com.watcha.watchapedia.model.entity.Recomment;
import com.watcha.watchapedia.model.entity.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record RecommentDto(
        Long id,
        Comment comment,
        User user,
        String recommName,
        String recommText,
        LocalDateTime recommRegDate
) {
    public static RecommentDto of(Long id, Comment comment, User user, String recommName, String recommText, LocalDateTime recommRegDate){
        return new RecommentDto(id, comment, user,recommName, recommText, recommRegDate);
    }

    public static RecommentDto from(Recomment recomment){
        return new RecommentDto(
          recomment.getRecommIdx(),
          recomment.getComment(),
          recomment.getUser(),
          recomment.getRecommName(), recomment.getRecommText(), recomment.getRecommRegDate()
        );
    }




}
