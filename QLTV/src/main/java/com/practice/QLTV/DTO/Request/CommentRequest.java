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
public class CommentRequest {
    @NotNull(message = "Post ID is required")
    private Integer idPost;

    @NotNull(message = "User ID is required")
    private Integer idUser;

    @NotBlank(message = "Text is required")
    private String text;
}
