package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowBook, Integer> {
    List<BorrowBook> findByUser(User user);
    List<BorrowBook> findByBook(Book book);
}

