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
public class BorrowBookResponse {
    private int maMuon;
    private Integer bookId;
    private Integer userId;
    private LocalDate doMuon;
    private String SDT;
    private String CCCD;
    private String note;
}
