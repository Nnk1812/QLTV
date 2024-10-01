package com.practice.QLTV.Service;

import com.practice.QLTV.Entity.Publisher;
import com.practice.QLTV.Repository.PublisherRepository;
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
public class PublisherService {
    PublisherRepository publisherRepository;
    public List<Publisher> getall(){
        return publisherRepository.findAll();
    }
}
