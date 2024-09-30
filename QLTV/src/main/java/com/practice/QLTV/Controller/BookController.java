package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.BookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.BookResponse;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    APIResponse<Book> createbook(@RequestBody @Valid BookRequest request) {
        APIResponse<Book> apiResponse = new APIResponse<>();
        apiResponse.setData(bookService.createBook(request));
        return apiResponse;
    }
    @GetMapping
    List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    Book getBookById(@RequestParam int id) {
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    BookResponse updateBook(@RequestBody @Valid BookRequest request, @PathVariable int id) {
        return bookService.updateBook(request,id);
    }
    @DeleteMapping
    String deleteBookById(@RequestParam int id) {
        return bookService.deleteBookById(id);
    }

}
