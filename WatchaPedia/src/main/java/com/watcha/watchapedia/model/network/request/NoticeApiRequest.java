package com.watcha.watchapedia.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NoticeApiRequest {
    private String ntcTitle;
    private String ntcImagepath;
    private String ntcText;
    private String ntcBtnText;
    private String ntcBtnColor;
    private String ntcRegBy;
}