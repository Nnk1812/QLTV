package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.ReturnBookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.Entity.ReturnBook;
import com.practice.QLTV.Service.ReturnBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/returnB")
public class ReturnBookController {
    @Autowired
    private ReturnBookService returnBookService;

    @PostMapping
    APIResponse<ReturnBook> createReturnBook(ReturnBookRequest request) {
        APIResponse<ReturnBook> apiResponse = new APIResponse<>();
        apiResponse.setData(returnBookService.createReturnBook(request));
        return apiResponse;
    }

    @GetMapping
    List<ReturnBook> getReturnBooks() {
        return returnBookService.getAllReturnBooks();
    }

    @GetMapping("/{id}")
    ReturnBook getReturnBookById(@PathVariable int id) {
        return returnBookService.getReturnBookById(id);
    }

    @PutMapping("/{id}")
    ReturnBook updateReturnBook(@PathVariable int id, ReturnBookRequest request) {
        return returnBookService.updateReturnBook(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteReturnBook(@PathVariable int id) {
        return returnBookService.deleteReturnBook(id);
    }
}
