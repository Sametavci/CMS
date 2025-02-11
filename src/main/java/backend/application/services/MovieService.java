package backend.application.services;

import backend.domain.ports.repositorys.IMovieRepository;
import backend.infrastructure.api.dto.MovieFilter;
import backend.domain.models.DomainMovie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService extends BaseService<DomainMovie, Long> {
    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
    }

    public List<DomainMovie> getMoviesByFilter(MovieFilter movieFilter) {
        return new ArrayList<>(movieRepository.getMoviesByFilter(movieFilter.getTitle(), movieFilter.getGenre()));
    }


    @Override
    public Optional<DomainMovie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
