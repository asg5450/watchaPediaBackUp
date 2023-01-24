package com.watcha.watchapedia.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdvertiseApiRequest {
    private String ad_idx;
    private String ad_title;
    private String ad_content;
    private String ad_status;
}
