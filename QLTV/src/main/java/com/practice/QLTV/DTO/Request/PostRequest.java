package com.practice.QLTV.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @NotNull(message = "User ID is required")
    private Integer userID;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Date of post is required")
    private LocalDate dop;

    @NotBlank(message = "Content is required")
    private String content;
}
