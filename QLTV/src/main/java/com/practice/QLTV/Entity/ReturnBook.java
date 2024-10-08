package com.practice.QLTV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "ReturnBook")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTra;

    @ManyToOne
    @JoinColumn(name = "maMuon", nullable = false)
    private BorrowBook borrow;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate doTra; // Date returned

    @Column
    private String note; // Note (optional)
}
