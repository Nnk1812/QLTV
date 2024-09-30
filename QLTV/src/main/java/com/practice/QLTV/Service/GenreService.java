package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.GenreRequest;
import com.practice.QLTV.DTO.Response.GenreResponse;
import com.practice.QLTV.Entity.Genre;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.GenreRepository;
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
public class GenreService {
    private GenreRepository genreRepository;
    private Mapper mapper;
    public Genre createGenre(GenreRequest request){
        Genre genre = mapper.toGenre(request);
        return genreRepository.save(genre);
    }

    public List<Genre> getall(){
        return genreRepository.findAll();
    }

    public Genre findbyid(int id){
        return genreRepository.findById(id).orElseThrow(()->new RuntimeException("Genre not found"));
    }

    public GenreResponse updateGenre(int id, GenreRequest request){

        Genre genre = genreRepository.findByGenreID(id).orElseThrow(()->new RuntimeException("Genre not found"));
        mapper.updateGerne(genre,request);
        return mapper.toGenreResponse(genreRepository.save(genre));
    }

    public  String deleteGenre(int id){
        Genre genre = genreRepository.findById(id).orElseThrow(()->new RuntimeException("Genre not found"));
        genreRepository.delete(genre);
        return "Genre deleted";
    }
}
