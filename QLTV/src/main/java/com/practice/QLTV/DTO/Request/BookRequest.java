package com.practice.QLTV.DTO.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private Integer bookId;
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Genre ID is required")
    private List<Integer> genreID;

    @NotNull(message = "Author ID is required")
    private Integer authorId;

    @NotNull(message = "Publisher ID is required")
    private Integer publisherID;

    @NotNull(message = "Amount is required")
    private Integer amount;
}
