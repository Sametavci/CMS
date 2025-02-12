package backend.application.services;

import backend.domain.ports.repositorys.IMovieRepository;
import backend.domain.models.DomainMovie;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends BaseService<DomainMovie, Long> {
    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
    }

}
