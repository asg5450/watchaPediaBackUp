package com.watcha.watchapedia.model.entity;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "tbComment")
@Builder
@Data
@ToString(callSuper = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commIdx; // 번호
    private Long commUserIdx; // 문의내용
    private String commName; // 사용자 번호
    @Column(length = 200)
    private String commText; // 사용자 이름
    private String commContentType; // 문의 날짜
    @Column
    private Long commContentIdx; // qna 등록
    @Column(length = 200)
    private LocalDateTime commRegDate; // qna 상태
    @Column(length = 2000)
    private String commImg; // qna 상세 페이지 이미지
    private String commDetext; // qna 상세 페이지 내용
    private String commUsername; // 코멘트 유저 이름



    protected Comment() {}
    private Comment(Long commIdx, Long commUserIdx, String commName, String commText,
                    String commContentType, Long commContentIdx, LocalDateTime commRegDate
                        , String commImg, String commDetext, String commUsername) {
        this.commIdx = commIdx;
        this.commUserIdx = commUserIdx;
        this.commName = commName;
        this.commText = commText;
        this.commContentType = commContentType;
        this.commContentIdx = commContentIdx;
        this.commRegDate = commRegDate;
        this.commImg = commImg;
        this.commDetext = commDetext;
        this.commUsername = commUsername;

    }

    public static Comment of(Long commIdx, Long commUserIdx, String commName, String commText,
                             String commContentType, Long commContentIdx, LocalDateTime commRegDate
            ,String commImg, String commDetext, String commUsername) {
        return new Comment(commIdx, commUserIdx, commName, commText,
                commContentType,commContentIdx,commRegDate,commImg,commDetext,commUsername );
    }

    @Override
    public int hashCode() {
        return Objects.hash(commIdx);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Comment comment)) return false;
        return commIdx != null;
    }
}
