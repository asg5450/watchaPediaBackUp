package com.watcha.watchapedia.model.network.response;



import com.watcha.watchapedia.model.dto.CommentDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CommentResponse(
        Long commIdx, Long commUserIdx, String commName, String commText,
        String commContentType, Long commContentIdx, LocalDateTime commRegDate
        ,String commImg, String commDetext, String commUsername
)implements Serializable {
    public static CommentResponse of(
            Long commIdx, Long commUserIdx, String commName, String commText,
            String commContentType, Long commContentIdx, LocalDateTime commRegDate
            ,String commImg, String commDetext, String commUsername){
        return new CommentResponse(commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate,commImg,commDetext,commUsername);
    }

    public static CommentResponse from (CommentDto dto){
        return new CommentResponse(
                dto.commIdx(),
                dto.commUserIdx(),
                dto.commName(),
                dto.commText(),
                dto.commContentType(),
                dto.commContentIdx(),
                dto.commRegDate(),
                dto.commImg(),
                dto.commDetext(),
                dto.commUsername()
        );
    }
}
