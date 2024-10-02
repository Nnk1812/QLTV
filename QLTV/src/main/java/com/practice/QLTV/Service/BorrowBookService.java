package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BorrowBookRequest;
import com.practice.QLTV.DTO.Response.*;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.BookRepository;
import com.practice.QLTV.Repository.BorrowRepository;
import com.practice.QLTV.Repository.UserRepository;
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
public class BorrowBookService {
    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    Mapper mapper;
    BorrowBookResponse toResponse(BorrowBook borrowBook) {
        BorrowBookResponse response = new BorrowBookResponse();
        response.setMaMuon(borrowBook.getMaMuon());

        BookResponse book = new BookResponse();
        book.setBookID(borrowBook.getBook().getBookID());
        book.setName(borrowBook.getBook().getName());
        book.setAmount(borrowBook.getBook().getAmount());

        GenreResponse genreDTO = new GenreResponse();
        genreDTO.setGenreID(borrowBook.getBook().getGenre().getGenreID());
        genreDTO.setName(borrowBook.getBook().getGenre().getName());
        book.setGenreID(genreDTO);

        AuthorResponse authorDTO = new AuthorResponse();
        authorDTO.setAuthorID(borrowBook.getBook().getAuthor().getAuthorID());
        authorDTO.setName(borrowBook.getBook().getAuthor().getName());
        authorDTO.setEmail(borrowBook.getBook().getAuthor().getEmail());
        authorDTO.setDob(borrowBook.getBook().getAuthor().getDob());
        authorDTO.setSDT(borrowBook.getBook().getAuthor().getSDT());
        book.setAuthorID(authorDTO);

        PublisherResponse publisherDTO = new PublisherResponse();
        publisherDTO.setPublisherID(borrowBook.getBook().getPublisher().getPublisherID());
        publisherDTO.setName(borrowBook.getBook().getPublisher().getName());
        publisherDTO.setAddress(borrowBook.getBook().getPublisher().getAddress());
        publisherDTO.setSDT(borrowBook.getBook().getPublisher().getSDT());
        book.setPublisherID(publisherDTO);
        response.setBookID(book);

        UserResponse user = new UserResponse();
        user.setUserId(borrowBook.getUser().getUserId());
        user.setName(borrowBook.getUser().getName());
        user.setUserName(borrowBook.getUser().getUserName());
        user.setPassWord(borrowBook.getUser().getPassWord());
        user.setSDT(borrowBook.getUser().getSDT());
        user.setDob(borrowBook.getUser().getDob());
        user.setAddress(borrowBook.getUser().getAddress());
        user.setCCCD(borrowBook.getUser().getCCCD());
        user.setDoc(borrowBook.getUser().getDoc());


        RoleResponse role = new RoleResponse();
        role.setRoleName(borrowBook.getUser().getUserRole().getRoleName());
        role.setDescription(borrowBook.getUser().getUserRole().getDescription());
        user.setRoleName(role);  // Set the UserRoleResponse object to UserResponse

        response.setUserID(user);


        response.setUserID(user);
        response.setDoMuon(borrowBook.getDoMuon());
        response.setSDT(borrowBook.getSDT());
        response.setCCCD(borrowBook.getCCCD());
        response.setNote(borrowBook.getNote());
        return response;
    }
    private BorrowBook toBorrowBook(BorrowBookRequest request) {
        BorrowBook borrowBook = new BorrowBook();

        // Thiết lập các thuộc tính cơ bản
        borrowBook.setMaMuon(request.getBorrowID());
        borrowBook.setDoMuon(request.getDoMuon());
        borrowBook.setSDT(request.getSDT());
        borrowBook.setCCCD(request.getCCCD());
        borrowBook.setNote(request.getNote());

        // Kiểm tra tồn tại của Book và User trước khi tạo BorrowBook
        Book book = bookRepository.findById(request.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        borrowBook.setBook(book);
        User user = userRepository.findById(request.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        borrowBook.setUser(user);


        return borrowBook;
    }

    public BorrowBook createborrow(BorrowBookRequest request) {
        BorrowBook book = toBorrowBook(request);
        return borrowRepository.save(book);
    }
    public List<BorrowBook> getall() {
        return borrowRepository.findAll();
    }
    public BorrowBook findById(int id) {
        return (borrowRepository.findByMaMuon(id).orElseThrow(()->new RuntimeException("BorrowBook not found")));
    }
    public BorrowBookResponse update(BorrowBookRequest request,int id) {
        BorrowBook book = borrowRepository.findByMaMuon(id).orElseThrow(()->new RuntimeException("Book not found"));
        mapper.updateBorrowBook(book,request);

        return mapper.toBorrowBookResponse(book);
    }
    public String delete(int id) {
        BorrowBook book = borrowRepository.findByMaMuon(id).orElseThrow(()->new RuntimeException("Book not found"));
        borrowRepository.delete(book);
        return "Book deleted";
    }
}
