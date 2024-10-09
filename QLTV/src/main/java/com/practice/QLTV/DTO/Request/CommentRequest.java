package com.practice.QLTV.DTO.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotNull(message = "Post ID is required")
    private Integer idPost;

    @NotNull(message = "User ID is required")
    private Integer idUser;

    @NotBlank(message = "Text is required")
    private String text;

    private LocalDate doCmt = LocalDate.now();

}
