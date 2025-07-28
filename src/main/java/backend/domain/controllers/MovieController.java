package backend.domain.controllers;

import backend.application.services.MovieService;
import backend.domain.models.DomainMovie;
import backend.infrastructure.persistence.entities.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController extends BaseController<DomainMovie, Long> {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        super(movieService);
        this.movieService = movieService;
    }

    @Override
    public Class<? extends BaseController<DomainMovie, Long>> getControllerClass() {
        return MovieController.class;
    }
}
