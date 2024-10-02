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

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReturnBookService {
    @Autowired
    private ReturnBookRepository repo;
    private Mapper mapper;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    ReturnBookResponse toReturn(ReturnBook returnBook) {
        ReturnBookResponse response = new ReturnBookResponse();

        // Create BorrowBookResponse from the ReturnBook's BorrowBook
        BorrowBook borrowBook = returnBook.getBorrow();
        BorrowBookResponse borrowBookResponse = new BorrowBookResponse();
        borrowBookResponse.setMaMuon(borrowBook.getMaMuon());

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
        borrowBookResponse.setBookID(book);

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
        user.setRoleName(role);

        borrowBookResponse.setUserID(user);

        borrowBookResponse.setDoMuon(borrowBook.getDoMuon());
        borrowBookResponse.setSDT(borrowBook.getSDT());
        borrowBookResponse.setCCCD(borrowBook.getCCCD());
        borrowBookResponse.setNote(borrowBook.getNote());


        response.setBorrowId(borrowBookResponse);
        response.setUserId(user);

        // Setting other fields of ReturnBookResponse
        response.setMaTra(returnBook.getMaTra());  // Getting maTra from ReturnBook
        response.setDoTra(returnBook.getDoTra());  // Getting doTra from ReturnBook
        response.setNote(returnBook.getNote());    // Getting note from ReturnBook

        return response;
    }

    ReturnBook toreturnBook(ReturnBookRequest request) {
        ReturnBook returnBook = new ReturnBook();
        BorrowBook borrowBook = borrowRepository.findByMaMuon(request.getBorrowID()).orElseThrow(
                ()-> new RuntimeException("ma muon not found"));
        returnBook.setBorrow(borrowBook);
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()->new RuntimeException("user not found"));
        returnBook.setUser(user);
        returnBook.setDoTra(request.getDoTra());
        returnBook.setNote(request.getNote());
        return returnBook;
    }
    public ReturnBook createReturnBook(ReturnBookRequest request) {
        ReturnBook returnBook = toreturnBook(request);
        return repo.save(returnBook);
    }
    public List<ReturnBook> getAllReturnBooks() {
        return repo.findAll();
    }
    public ReturnBook getReturnBookById(int id) {
        return repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
    }
    public ReturnBookResponse updateReturnBook(int id, ReturnBookRequest request) {
        ReturnBook returnBook = repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
        mapper.updateReturnBook(returnBook,request);
        return toReturn(repo.save(returnBook));
    }
    public String deleteReturnBook(int id) {
        ReturnBook returnBook = repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
        repo.delete(returnBook);
        return "ReturnBook deleted";
    }
}
