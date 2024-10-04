package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.BorrowBookRequest;
import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.BookRepository;
import com.practice.QLTV.Repository.BorrowRepository;
import com.practice.QLTV.Repository.UserRepository;
import com.practice.QLTV.Repository.projection.BorrowBookView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowBookService {
    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private void updateBorrowBook(BorrowBook borrowBook, BorrowBookRequest request) {
        // Cập nhật thông tin mượn sách
        borrowBook.setDoMuon(request.getDoMuon());
        borrowBook.setSDT(request.getSDT());
        borrowBook.setCCCD(request.getCCCD());
        borrowBook.setNote(request.getNote());

        // Tìm kiếm và cập nhật đối tượng Book nếu có sự thay đổi
        Book book = bookRepository.findById(request.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        borrowBook.setBook(book);

        // Tìm kiếm và cập nhật đối tượng User nếu có sự thay đổi
        User user = userRepository.findById(request.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        borrowBook.setUser(user);
    }

    public BorrowBook toBookBorrow(BorrowBookRequest request) {
        BorrowBook borrowBook = new BorrowBook();

        Book book = bookRepository.findById(request.getBookID()).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
        borrowBook.setBook(book);

        User user = userRepository.findById(request.getUserID()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        borrowBook.setUser(user);

        // Gán giá trị cho các trường còn lại
        borrowBook.setDoMuon(request.getDoMuon());
        borrowBook.setSDT(request.getSDT());
        borrowBook.setCCCD(request.getCCCD());
        borrowBook.setNote(request.getNote());

        return borrowBook;
    }


    public BorrowBook createborrow(BorrowBookRequest request) {
        BorrowBook book = toBookBorrow(request);
        return borrowRepository.save(book);
    }
    public List<BorrowBook> getall() {
        return borrowRepository.findAll();
    }
    public BorrowBook findById(int id) {
        return borrowRepository.findByMaMuon(id).orElse(null);
    }
    public BorrowBook update(BorrowBookRequest request,int id) {
        BorrowBook book = borrowRepository.findByMaMuon(id).orElseThrow(()->new RuntimeException("Book not found"));
        updateBorrowBook(book,request);
        return (borrowRepository.save(book));
    }
    public String delete(int id) {
        BorrowBook book = borrowRepository.findByMaMuon(id).orElseThrow(()->new RuntimeException("Book not found"));
        borrowRepository.delete(book);
        return "Book deleted";
    }

    public List<BorrowBookView> countborrow(LocalDate startdate,LocalDate enddate)
    {
        return borrowRepository.countBorrowBookByBookId(startdate,enddate);
    }

}
