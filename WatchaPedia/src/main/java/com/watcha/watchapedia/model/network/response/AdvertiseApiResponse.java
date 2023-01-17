package com.watcha.watchapedia.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertiseApiResponse {
    private Long id;
    private String adTitle;
    private String adContent;
    private String adStatus;
}
