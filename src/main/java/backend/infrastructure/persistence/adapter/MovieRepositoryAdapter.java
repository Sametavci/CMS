package backend.infrastructure.persistence.adapter;

import backend.domain.models.DomainHall;
import backend.domain.ports.repositorys.IMovieRepository;
import backend.infrastructure.persistence.entities.Hall;
import backend.infrastructure.persistence.repositorys.IMovieJpaRepository;
import backend.domain.models.DomainMovie;
import backend.infrastructure.persistence.entities.Movie;
import backend.infrastructure.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryAdapter implements IMovieRepository {

    private final IMovieJpaRepository movieJpaRepository;
    private final MovieMapper mapper;

    public MovieRepositoryAdapter(IMovieJpaRepository movieJpaRepository, MovieMapper mapper) {
        this.movieJpaRepository = movieJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DomainMovie> findAll() {
        return movieJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DomainMovie> findById(Long id) {
        return movieJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public DomainMovie save(DomainMovie domainMovie) {
        Movie entity = mapper.toEntity(domainMovie);
        return mapper.toDomain(movieJpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        movieJpaRepository.deleteById(id);
    }

    public DomainMovie update(DomainMovie dto, Long id){
        DomainMovie databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Movie entity = mapper.toEntity(databaseElement);
        mapper.update(entity, dto);
        Movie savedEntity = movieJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);

    }
}
