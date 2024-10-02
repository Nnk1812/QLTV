package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.ReturnBookRequest;
import com.practice.QLTV.DTO.Response.*;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.ReturnBook;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.BorrowRepository;
import com.practice.QLTV.Repository.ReturnBookRepository;
import com.practice.QLTV.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReturnBookService {
    @Autowired
    private ReturnBookRepository repo;
    private Mapper mapper;
    private BorrowRepository borrowRepository;
    private UserRepository userRepository;
    ReturnBookResponse toReturn(ReturnBook returnBook) {
        ReturnBookResponse response = new ReturnBookResponse();

        // Set maTra (mã trả)
        response.setMaTra(returnBook.getMaTra());

        // Set thông tin sách
        BorrowBook borrowBook = returnBook.getBorrow();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookID(borrowBook.getBook().getBookID());
        bookResponse.setName(borrowBook.getBook().getName());
        bookResponse.setAmount(borrowBook.getBook().getAmount());

        GenreResponse genreDTO = new GenreResponse();
        genreDTO.setGenreID(borrowBook.getBook().getGenre().getGenreID());
        genreDTO.setName(borrowBook.getBook().getGenre().getName());
        bookResponse.setGenreID(genreDTO);

        AuthorResponse authorDTO = new AuthorResponse();
        authorDTO.setAuthorID(borrowBook.getBook().getAuthor().getAuthorID());
        authorDTO.setName(borrowBook.getBook().getAuthor().getName());
        authorDTO.setEmail(borrowBook.getBook().getAuthor().getEmail());
        authorDTO.setDob(borrowBook.getBook().getAuthor().getDob());
        authorDTO.setSDT(borrowBook.getBook().getAuthor().getSDT());
        bookResponse.setAuthorID(authorDTO);

        PublisherResponse publisherDTO = new PublisherResponse();
        publisherDTO.setPublisherID(borrowBook.getBook().getPublisher().getPublisherID());
        publisherDTO.setName(borrowBook.getBook().getPublisher().getName());
        publisherDTO.setAddress(borrowBook.getBook().getPublisher().getAddress());
        publisherDTO.setSDT(borrowBook.getBook().getPublisher().getSDT());
        bookResponse.setPublisherID(publisherDTO);

        response.setMaBook(bookResponse);

        // Set thông tin người dùng
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(returnBook.getUser().getUserId());
        userResponse.setName(returnBook.getUser().getName());
        userResponse.setUserName(returnBook.getUser().getUserName());
        userResponse.setPassWord(returnBook.getUser().getPassWord());
        userResponse.setSDT(returnBook.getUser().getSDT());
        userResponse.setDob(returnBook.getUser().getDob());
        userResponse.setAddress(returnBook.getUser().getAddress());
        userResponse.setCCCD(returnBook.getUser().getCCCD());
        userResponse.setDoc(returnBook.getUser().getDoc());

        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoleName(returnBook.getUser().getUserRole().getRoleName());
        roleResponse.setDescription(returnBook.getUser().getUserRole().getDescription());
        userResponse.setRoleName(roleResponse);

        response.setUserId(userResponse);

        // Set ngày trả và ghi chú
        response.setDoTra(returnBook.getDoTra());
        response.setNote(returnBook.getNote());

        return response;
    }
    ReturnBook toReturn(ReturnBookRequest request) {
        ReturnBook returnBook = new ReturnBook();
        returnBook.setMaTra(request.getMaTRA());
        BorrowBook borrow = borrowRepository.findByMaMuon(request.getBorrowID()).orElseThrow(
                ()->new RuntimeException("ma muon not found")
        );
        returnBook.setBorrow(borrow);
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()->new RuntimeException("user not found")
        );
        returnBook.setUser(user);
        returnBook.setDoTra(request.getDoTra());
        returnBook.setNote(request.getNote());

        return returnBook;
    }
    void updateReturnBook(ReturnBook returnBook,ReturnBookRequest request) {
        BorrowBook borrow = borrowRepository.findByMaMuon(request.getBorrowID()).orElseThrow(
                ()->new RuntimeException("ma muon not found")
        );
        returnBook.setBorrow(borrow);
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()->new RuntimeException("user not found")
        );
        returnBook.setUser(user);
        returnBook.setDoTra(request.getDoTra());
        returnBook.setNote(request.getNote());

    }

    public ReturnBook createReturnBook(ReturnBookRequest request) {
        ReturnBook returnBook = toReturn(request);
        return repo.save(returnBook);
    }
    public List<ReturnBook> getAllReturnBooks() {
        return repo.findAll();
    }
    public ReturnBook getReturnBookById(int id) {
        return repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
    }
    public ReturnBook updateReturnBook(int id, ReturnBookRequest request) {
        ReturnBook returnBook = repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
        updateReturnBook(returnBook,request);
        return repo.save(returnBook);
    }
    public String deleteReturnBook(int id) {
        ReturnBook returnBook = repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
        repo.delete(returnBook);
        return "ReturnBook deleted";
    }
}
