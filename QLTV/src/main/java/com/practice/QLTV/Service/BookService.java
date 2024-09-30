package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.BookResponse;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    private BookRepository bookRepository;
    private Mapper mapper;

    public Book createBook(BookRequest request) {
        Book book = mapper.toBook(request);
        return bookRepository.save(book);
    }
    public BookResponse updateBook(BookRequest request, int id) {
        Book book = bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found"));
        mapper.updateBook(book,request);
        return mapper.toBookResponse(bookRepository.save(book));
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }
    public String deleteBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.delete(book);
        return "Book deleted";
    }
}
