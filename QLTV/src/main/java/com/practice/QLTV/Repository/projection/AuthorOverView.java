package com.practice.QLTV.Repository.projection;

import com.practice.QLTV.Entity.Book;

public interface AuthorOverView {
    Integer getAuthorID();
    String getName();
    String getSDT();

    Book getBooks();

}
