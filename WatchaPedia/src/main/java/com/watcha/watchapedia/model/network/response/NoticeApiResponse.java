package com.watcha.watchapedia.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeApiResponse {
    private Long ntcIdx;
    private String ntcTitle;
    private String ntcText;
    private String ntcRegBy;
    private String status;
    private String ntcImagepath;
    private String ntcBtnColor;
    private String ntcBtnText;
    private String ntcBtnLink;
}
