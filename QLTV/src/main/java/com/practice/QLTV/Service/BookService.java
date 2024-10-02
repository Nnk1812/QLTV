package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BookRequest;
import com.practice.QLTV.DTO.Request.GenreRequest;
import com.practice.QLTV.DTO.Response.AuthorResponse;
import com.practice.QLTV.DTO.Response.BookResponse;
import com.practice.QLTV.DTO.Response.GenreResponse;
import com.practice.QLTV.DTO.Response.PublisherResponse;
import com.practice.QLTV.Entity.Author;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.Genre;
import com.practice.QLTV.Entity.Publisher;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.AuthorRepository;
import com.practice.QLTV.Repository.BookRepository;
import com.practice.QLTV.Repository.GenreRepository;
import com.practice.QLTV.Repository.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    GenreRepository genreRepository;
    PublisherRepository publisherRepository;
    AuthorRepository authorRepository;

     Book toBook(BookRequest request) {
        Book book = new Book();

        book.setName(request.getName());

        Genre genre = genreRepository.findByGenreID(request.getGenreID()).orElseThrow(
                ()->new RuntimeException("genre not exists"));
        book.setGenre(genre);

        Author author = authorRepository.findByAuthorID(request.getAuthorId()).orElseThrow(
                ()->new RuntimeException("author not exists"));
        book.setAuthor(author);
        Publisher publisher = publisherRepository.findByPublisherID(request.getPublisherID()).orElseThrow(
                ()->new RuntimeException("publisher not exists")
        );
        book.setPublisher(publisher);

        book.setAmount(request.getAmount());

        return book;
    }
    private void updateBook(Book book, BookRequest request) {
        book.setName(request.getName());

        Genre genre = genreRepository.findById(request.getGenreID())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        book.setGenre(genre);

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);

        Publisher publisher = publisherRepository.findById(request.getPublisherID())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        book.setPublisher(publisher);

        book.setAmount(request.getAmount());
    }
    public BookRequest createBook(BookRequest request) {
        Book book = toBook(request);
        bookRepository.save(book);
        return request;
    }
    public Book updateBook(BookRequest request, Integer id) {
        Book book = bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found"));
        updateBook(book,request);
        return (bookRepository.save(book));
    }
    public List<Book> getAllBooks() {
        return (bookRepository.findAll());
    }
    public Book getBookById(Integer id) {
        return (bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found")));
    }
    public String deleteBookById(Integer id) {
        Book book = bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.delete(book);
        return "Book deleted";
    }
}
