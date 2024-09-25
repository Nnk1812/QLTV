package com.practice.QLTV.DTO.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Genre ID is required")
    private Integer genreID;

    @NotNull(message = "Author ID is required")
    private Integer authorID;

    @NotNull(message = "Publisher ID is required")
    private Integer publisherID;

    @NotNull(message = "Amount is required")
    private Integer amount;
}
