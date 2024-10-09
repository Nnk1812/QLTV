package com.practice.QLTV.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "User name is mandatory")
    @Size(min = 3, max = 30, message = "User name must be between 3 and 30 characters")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String passWord;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^(\\d{10}|\\d{11})$", message = "Phone number must be 10 or 11 digits")
    private String SDT; // Phone number


    private LocalDate dob = LocalDate.now();

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Citizen ID is mandatory")
    @Size(min = 9, max = 12, message = "Citizen ID must be between 9 and 12 characters")
    private String CCCD; // Citizen ID

    private LocalDate doc = LocalDate.now();; // Document (optional)
    @NotBlank(message = "roleName ID is mandatory")
    private String roleName;
}
