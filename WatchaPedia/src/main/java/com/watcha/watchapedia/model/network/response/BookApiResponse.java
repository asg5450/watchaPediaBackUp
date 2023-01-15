package com.watcha.watchapedia.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookApiResponse {
    private Long bookIdx;
    private String bookTitle;
    private String bookWriter;
    private String bookAtDate;
    private String bookCategory;
    private String bookPage;
}
