package com.practice.QLTV.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private int idCmt;
    private int idPost;
    private int idUser;
    private String userName; // Thêm tên người dùng để dễ hiểu
    private String text;
    private LocalDate docmt; // Ngày bình luận
}
