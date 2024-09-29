package com.practice.QLTV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String SDT; // Phone number

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dob; // Date of birth

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String CCCD; // Citizen ID

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate doc ;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BorrowBook> borrows;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    @ManyToOne
    @JoinColumn(name = "roleName") //
    private Role userRole;

}
