package com.practice.QLTV.Repository.projection;

import com.practice.QLTV.Entity.Book;

public interface BorrowBookView {
    Integer getBookID();
    String getName();
    Integer getBorrowCount();
}
