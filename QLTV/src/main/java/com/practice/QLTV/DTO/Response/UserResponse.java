package com.practice.QLTV.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;

    private String userName;

    private String passWord;

    private String name;

    private String SDT; // Phone number

    private LocalDate dob; // Date of birth

    private String address;

    private String CCCD; // Citizen ID

    private LocalDate doc ; // Document (optional)

    private String roleName;
    
}
