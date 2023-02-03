package com.watcha.watchapedia.model.network.response;

import com.watcha.watchapedia.model.dto.RecommentDto;
import com.watcha.watchapedia.model.entity.Comment;
import com.watcha.watchapedia.model.entity.Recomment;
import com.watcha.watchapedia.model.entity.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record RecommentResponseDto(
        Long id,
        Comment comment,
        User user,
        String recommName,
        String recommText,
        String regDateAgo
) {
    public static RecommentResponseDto of(Long id, Comment comment, User user, String recommName, String recommText, String regDateAgo){
        return new RecommentResponseDto(id, comment, user,recommName, recommText, regDateAgo);
    }

    public static RecommentResponseDto from(RecommentDto recomment){
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime regDay = recomment.recommRegDate().truncatedTo(ChronoUnit.DAYS);
        Long sicha = ChronoUnit.DAYS.between(regDay,today); //오른쪽에서 왼쪽 뺀 값이 Long 형으로 나옴
        String regDateAgo = "";
        if(sicha == 0){
            regDateAgo = "오늘";
        }else{
            regDateAgo = sicha + "일전";
        }
        return new RecommentResponseDto(
          recomment.id(),
          recomment.comment(),
          recomment.user(),
          recomment.recommName(), recomment.recommText(), regDateAgo
        );
    }

    public static RecommentResponseDto dayAgo(RecommentDto recomment, String dayAgo){
        return new RecommentResponseDto(
                recomment.id(),
                recomment.comment(),
                recomment.user(),
                recomment.recommName(), recomment.recommText(), dayAgo
        );
    }




}
