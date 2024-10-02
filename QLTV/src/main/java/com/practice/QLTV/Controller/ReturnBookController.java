package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.ReturnBookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.ReturnBookResponse;
import com.practice.QLTV.Entity.ReturnBook;
import com.practice.QLTV.Service.ReturnBookService;
import jakarta.validation.Valid;
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
    APIResponse<ReturnBook> createReturnBook(@RequestBody @Valid ReturnBookRequest request) {
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
    ReturnBookResponse updateReturnBook(@PathVariable int id,@RequestBody ReturnBookRequest request) {
        return returnBookService.updateReturnBook(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteReturnBook(@PathVariable int id) {
        return returnBookService.deleteReturnBook(id);
    }
}
