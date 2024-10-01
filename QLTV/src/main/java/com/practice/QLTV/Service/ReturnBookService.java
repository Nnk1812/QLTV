package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.ReturnBookRequest;
import com.practice.QLTV.Entity.ReturnBook;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.ReturnBookRepository;
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
    public ReturnBook createReturnBook(ReturnBookRequest request) {
        ReturnBook returnBook = mapper.toReturnBook(request);
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
        mapper.updateReturnBook(returnBook,request);
        return repo.save(returnBook);
    }
    public String deleteReturnBook(int id) {
        ReturnBook returnBook = repo.findBymaTra(id).orElseThrow(()-> new RuntimeException("ReturnBook not found"));
        repo.delete(returnBook);
        return "ReturnBook deleted";
    }
}
