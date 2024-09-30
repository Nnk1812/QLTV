package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.GenreRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.GenreResponse;
import com.practice.QLTV.Entity.Genre;
import com.practice.QLTV.Service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @PostMapping
    APIResponse<Genre> addGenre(@RequestBody @Valid GenreRequest request) {
        APIResponse<Genre> apiResponse = new APIResponse<>();
        apiResponse.setData(genreService.createGenre(request));
        return apiResponse;
    }
    @GetMapping
    List<Genre> getAllGenre() {
        return genreService.getall();
    }
    @GetMapping("/{id}")
    Genre getGenreById(@PathVariable int id) {
         return genreService.findbyid(id);
    }
    @PutMapping("/{id}")
    GenreResponse updateGenre(@PathVariable int id, @RequestBody @Valid GenreRequest request) {
        return genreService.updateGenre(id, request);
    }
    @DeleteMapping("/{id}")
    String deleteGenreById(@PathVariable int id) {
        return genreService.deleteGenre(id);
    }
}
