package backend.domain.controllers;

import backend.application.services.MovieService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainMovie;
import backend.infrastructure.api.dto.MovieFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
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
    @Override
    public Class<? extends BaseController<DomainMovie, Long>> getControllerClass() {
        return MovieController.class;
    }
}
