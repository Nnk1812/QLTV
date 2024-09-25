package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(Genre genre);
}

