package com.watcha.watchapedia.model.dto;

import com.watcha.watchapedia.model.entity.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        Long commIdx,
        Long commUserIdx,
        String commName,
        String commText,
        String commContentType,
        Long commContentIdx,
        LocalDateTime commRegDate

) {
    public static CommentDto of(
            Long commIdx, Long commUserIdx, String commName, String commText,
            String commContentType, Long commContentIdx, LocalDateTime commRegDate
    ){

        return new CommentDto(commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate
        );
    }
    public static CommentDto of(
            Long commIdx, Long commUserIdx, String commName, String commText,
            String commContentType, LocalDateTime commRegDate

    ){

        return new CommentDto(commIdx, commUserIdx, commName, commText,
                commContentType,null,commRegDate
        );
    }

    public static CommentDto from(Comment entity){
        return new CommentDto(
                entity.getCommIdx(),
                entity.getCommUserIdx(),
                entity.getCommName(),
                entity.getCommText(),
                entity.getCommContentType(),
                entity.getCommContentIdx(),
                entity.getCommRegDate()


        );
    }
    public Comment toEntity(){
        return  Comment.of(
                commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate
        );
    }
}
