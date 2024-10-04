package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Genre;
import com.practice.QLTV.Repository.projection.GenreView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByGenreID(int genreID);
    @Query(value = "Select g.genreid, g.name, count(b.bookid) as genreCount"+
            " from genre g" +
            " join book b on g.genreid = b.genreid" +
            " join borrow bb on b.bookid = bb.bookID" +
            " Where do_muon between :startdate and :enddate" +
            " group by g.genreid , g.name " +
            " order by genreCount desc "
            ,nativeQuery = true)
    List<GenreView> countGenreViewByGenreID(@Param("startdate") LocalDate startdate, @Param("enddate") LocalDate enddate);
}

