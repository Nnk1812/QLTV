package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Book;
import com.practice.QLTV.Entity.BorrowBook;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.projection.BorrowBookView;
import com.practice.QLTV.Repository.projection.GenreView;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowBook, Integer> {
    List<BorrowBook> findByUser(User user);
    List<BorrowBook> findByBook(Book book);
    Optional<BorrowBook> findByMaMuon(int maMuon);

//    BorrowBookView findBorrowBookViewByMaMuon(int maMuon);

    @Query(value = "SELECT b.bookid, b.name, COUNT(bb.ma_muon) AS borrowCount" +
            " From borrow bb"+
            " left join book b on b.bookid = bb.bookID " +
            " Where do_muon Between :startdate and :enddate " +
            "GROUP BY bb.bookid , b.bookid, b.name "
            + " Order by borrowCount desc"
            ,nativeQuery = true)
    List<BorrowBookView> countBorrowBookByBookId(@Param("startdate") LocalDate startdate, @Param("enddate") LocalDate enddate);


}

