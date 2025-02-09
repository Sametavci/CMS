package com.infrastructure.persistence.adapter;

import com.domain.models.DomainMovie;
import com.domain.ports.repositorys.IMovieRepository;
import com.infrastructure.persistence.entities.Movie;
import com.infrastructure.persistence.mapper.MovieMapper;
import com.infrastructure.persistence.repositorys.IMovieJpaRepository;
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

    @Override
    public List<DomainMovie> getMoviesByFilter(String title, String genre) {
        return null;
    }
}
