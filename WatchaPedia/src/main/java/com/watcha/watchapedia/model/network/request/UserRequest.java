package com.watcha.watchapedia.dto.request;

import com.watcha.watchapedia.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserRequest(
        String userId,
        String userPw,
        Long userSsn1,
        Long userSsn2,
        String userEmail,
        String userName
){
  public static UserRequest of(String userId, String userPw, Long userSsn1, Long userSsn2,
                               String userEmail, String userName) {
    return new UserRequest(userId, userPw, userSsn1, userSsn2, userEmail, userName);
  }

  public UserDto toDto() {
    return UserDto.of(
            userId, userPw, userSsn1, userSsn2,
            userEmail, userName
    );
  }
}
