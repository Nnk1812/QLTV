package com.practice.QLTV.Repository;

import com.practice.QLTV.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByName(String name);
    Optional<Publisher> findByPublisherID(Integer publisherID);
}

