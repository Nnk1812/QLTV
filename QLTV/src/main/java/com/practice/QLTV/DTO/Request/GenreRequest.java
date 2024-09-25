package com.practice.QLTV.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "address is required")
    private String address;

    @Pattern(regexp = "^(\\d{10}|\\d{11})$", message = "Phone number must be 10 or 11 digits")
    @NotBlank(message = "SDT is required")
    private String SDT;

    @NotBlank(message = "date of birth is required")
    private LocalDate dob;
}
