package com.watcha.watchapedia.model.dto;

import com.watcha.watchapedia.model.entity.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        Long commIdx, Long commUserIdx, String commName, String commText,
        String commContentType, Long commContentIdx, LocalDateTime commRegDate
        ,String commImg, String commDetext, String commUsername
) {
    public static CommentDto of(
            Long commIdx, Long commUserIdx, String commName, String commText,
            String commContentType, Long commContentIdx, LocalDateTime commRegDate
            ,String commImg, String commDetext, String commUsername
    ){

        return new CommentDto(commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate,commImg,commDetext,commUsername
        );
    }
    public static CommentDto of(
            Long commIdx, Long commUserIdx, String commName, String commText,
            String commContentType, LocalDateTime commRegDate
            ,String commImg, String commDetext, String commUsername
    ){

        return new CommentDto(commIdx, commUserIdx, commName, commText,
                commContentType,null,commRegDate,commImg,commDetext,commUsername
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
                entity.getCommRegDate(),
                entity.getCommImg(),
                entity.getCommDetext(),
                entity.getCommUsername()

        );
    }
    public Comment toEntity(){
        return  Comment.of(
                commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate
                ,commImg,commDetext,commUsername


        );
    }
}
