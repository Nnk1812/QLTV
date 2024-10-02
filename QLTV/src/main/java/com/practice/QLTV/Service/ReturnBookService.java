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
