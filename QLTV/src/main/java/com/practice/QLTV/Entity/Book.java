package com.practice.QLTV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "Book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book_genre",  // Tên bảng trung gian là "book_genre"
            joinColumns = @JoinColumn(name = "bookID"),  // Cột khóa ngoại liên kết với Book
            inverseJoinColumns = @JoinColumn(name = "genreID")  // Cột khóa ngoại liên kết với Genre
    )
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "authorID", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisherID", nullable = false)
    private Publisher publisher;

    @Column(nullable = false)
    private int amount;

}
