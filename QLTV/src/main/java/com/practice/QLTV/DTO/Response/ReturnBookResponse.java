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
public class ReturnBookResponse {
    private Integer maTra;
    private BookResponse maBook;
    private UserResponse userId;
    private LocalDate doTra;
    private String note;
}
