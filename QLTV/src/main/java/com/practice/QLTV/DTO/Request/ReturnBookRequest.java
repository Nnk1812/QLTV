package com.practice.QLTV.DTO.Request;

import jakarta.validation.constraints.NotNull;
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
public class ReturnBookRequest {
    @NotNull(message = "Borrow ID is required")
    private Integer maMuon;

    @NotNull(message = "Return date is required")
    private LocalDate doTra; // Return date

    private String note; // Note (optional)
}
