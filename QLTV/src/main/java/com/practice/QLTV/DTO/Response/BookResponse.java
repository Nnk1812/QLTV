package com.practice.QLTV.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Integer bookID;
    private String name;
    private GenreResponse genreID;
    private AuthorResponse authorID;
    private PublisherResponse publisherID;
    private int amount;
}
