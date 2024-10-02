package com.practice.QLTV.DTO.Response;

import com.practice.QLTV.Entity.User;
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
public class BorrowBookResponse {
    private Integer maMuon;
    private BookResponse bookID;
    private UserResponse userID;
    private LocalDate doMuon;
    private String SDT;
    private String CCCD;
    private String note;
}
