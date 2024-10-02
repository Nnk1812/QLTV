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

     BookResponse toBookDTO(Book book) {
        BookResponse bookDTO = new BookResponse();
        bookDTO.setBookID(book.getBookID());
        bookDTO.setName(book.getName());

        GenreResponse genreDTO = new GenreResponse();
        genreDTO.setGenreID(book.getGenre().getGenreID());
        genreDTO.setName(book.getGenre().getName());
        bookDTO.setGenreID(genreDTO);

        AuthorResponse authorDTO = new AuthorResponse();
        authorDTO.setAuthorID(book.getAuthor().getAuthorID());
        authorDTO.setName(book.getAuthor().getName());
        authorDTO.setEmail(book.getAuthor().getEmail());
        authorDTO.setDob(book.getAuthor().getDob());
        authorDTO.setSDT(book.getAuthor().getSDT());
        bookDTO.setAuthorID(authorDTO);

        PublisherResponse publisherDTO = new PublisherResponse();
        publisherDTO.setPublisherID(book.getPublisher().getPublisherID());
        publisherDTO.setName(book.getPublisher().getName());
        publisherDTO.setAddress(book.getPublisher().getAddress());
        publisherDTO.setSDT(book.getPublisher().getSDT());
        bookDTO.setPublisherID(publisherDTO);

        bookDTO.setAmount(book.getAmount());
        return bookDTO;
    }
     List<BookResponse> toBookDTOList(List<Book> books) {
        return books.stream()
                .map(this::toBookDTO) // Sử dụng phương thức toBookDTO để chuyển đổi từng Book thành BookResponse
                .collect(Collectors.toList()); // Thu thập kết quả vào một danh sách
    }
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
    public BookResponse updateBook(BookRequest request, Integer id) {
        Book book = bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found"));
        updateBook(book,request);
        return toBookDTO(bookRepository.save(book));
    }
    public List<Book> getAllBooks() {
        return (bookRepository.findAll());
    }
    public BookResponse getBookById(Integer id) {
        return toBookDTO(bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found")));
    }
    public String deleteBookById(Integer id) {
        Book book = bookRepository.findByBookID(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.delete(book);
        return "Book deleted";
    }
}
