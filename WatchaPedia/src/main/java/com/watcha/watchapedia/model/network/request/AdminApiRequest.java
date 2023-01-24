package com.watcha.watchapedia.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdminApiRequest {
    private String adminName;
    private String adminId; // asg
    private String adminPw;
    private String adminNumber;
    private String adminType;
}
