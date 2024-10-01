package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BorrowBookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.BorrowBookResponse;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.BorrowRepository;
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
    private Mapper mapper;
    public BorrowBook createborrow(BorrowBookRequest request) {
        BorrowBook book = mapper.toBorrowBook(request);
        return borrowRepository.save(book);
    }
    public List<BorrowBook> getall() {
        return borrowRepository.findAll();
    }
    public BorrowBook findById(int id) {
        return borrowRepository.findByMaMuon(id).orElse(null);
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
