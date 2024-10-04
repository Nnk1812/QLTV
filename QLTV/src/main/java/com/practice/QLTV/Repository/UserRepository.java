package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.projection.UserView;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

    @Query(value = "select u.user_id , u.name , count(b.bookid) as countUser" +
            " from user u " +
            "join borrow b on u.user_id  = b.userid" +
            " where do_muon between :startdate and :enddate " +
            "group by u.user_id, u.name " +
            "order by countUser desc"
            ,nativeQuery = true)
    List<UserView> countUserViewByDate(@Param("startdate") LocalDate startdate, @Param("enddate") LocalDate enddate);
}

