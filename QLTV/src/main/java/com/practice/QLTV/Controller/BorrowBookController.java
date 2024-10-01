package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.BorrowBookRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.BorrowBookResponse;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Service.BorrowBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/borrowB")
public class BorrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;
    @PostMapping
    APIResponse<BorrowBook> creaeteBorrowBook (@RequestBody @Valid BorrowBookRequest request) {
        APIResponse<BorrowBook> apiResponse = new APIResponse<>();
        apiResponse.setData(borrowBookService.createborrow(request));
        return apiResponse;
    }
    @GetMapping
    List<BorrowBook> getAllBorrowBook () {
        return borrowBookService.getall();
    }
    @GetMapping("/{id}")
    BorrowBook getBorrowBook (@PathVariable int id) {
        return borrowBookService.findById(id);
    }
    @PutMapping("/{id}")
    BorrowBookResponse updateBorrowBook (@PathVariable int id, @RequestBody @Valid BorrowBookRequest request) {
        return borrowBookService.update(request,id);
    }
    @DeleteMapping("/{id}")
    String deleteBorrowBook (@PathVariable int id) {
        return borrowBookService.delete(id);
    }
}
