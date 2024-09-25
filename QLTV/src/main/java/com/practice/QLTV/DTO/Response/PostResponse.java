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
public class PostResponse {
    private int postId;
    private int userID;
    private String userName; // Thêm tên người dùng để dễ hiểu
    private String title;
    private String content;
    private LocalDate dop; // Ngày tạo bài viết
}
