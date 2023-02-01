package com.watcha.watchapedia.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CharacterApiRequest {
    private Long perIdx;
    private String perName;
    private String perPhoto;
    private String perBiography;
}
