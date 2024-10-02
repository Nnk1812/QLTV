package com.practice.QLTV.DTO.Request;


import jakarta.validation.constraints.NotBlank;
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
public class BorrowBookRequest {
    private Integer borrowID;

    @NotNull(message = "Book ID is required")
    private Integer bookID;

    @NotNull(message = "User ID is required")
    private Integer userID;

    @NotNull(message = "Borrow date is required")
    private LocalDate doMuon= LocalDate.now(); // Borrow date

    @NotBlank(message = "Phone number is required")
    private String SDT; // Phone number

    @NotBlank(message = "Citizen ID is required")
    private String CCCD; // Citizen ID

    private String note; // Note (optional)
}
