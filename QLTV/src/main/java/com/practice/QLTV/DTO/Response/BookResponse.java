package com.practice.QLTV.DTO.Response;

import com.practice.QLTV.Entity.Publisher;
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
    private Integer genreID;
    private Integer authorID;
    private Integer publisherID;
    private int amount;
}
