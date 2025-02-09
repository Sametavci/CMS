package com.domain.controllers;

import com.application.services.MovieService;
import com.domain.models.DomainMovie;
import com.infrastructure.api.dto.MovieFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController extends BaseController<DomainMovie, Long> {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        super(movieService);
        this.movieService = movieService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<DomainMovie>> searchMovie(@RequestBody MovieFilter movieFilter) {
        return ResponseEntity.ok(movieService.getMoviesByFilter(movieFilter));
    }
}
