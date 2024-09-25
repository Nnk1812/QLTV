package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.ReturnBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnBookRepository extends JpaRepository<ReturnBook, Integer> {
    List<ReturnBook> findByBorrow(BorrowBook borrow);
}

