package com.infrastructure.persistence.adapter;

import com.domain.models.DomainMovie;
import com.domain.ports.repositorys.BaseRepository;
import com.domain.ports.repositorys.IMovieRepository;
import com.infrastructure.persistence.entities.Movie;
import com.infrastructure.persistence.mapper.MovieMapper;
import com.infrastructure.persistence.repositorys.IMovieJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MovieRepositoryAdapter implements IMovieRepository {

    private final IMovieJpaRepository movieJpaRepository;
    private final MovieMapper mapper;

    @Autowired
    public MovieRepositoryAdapter(BaseRepository<Movie, Long> movieJpaRepository, MovieMapper mapper) {
        this.movieJpaRepository = (IMovieJpaRepository) movieJpaRepository; // Cast ile düzeltme
        this.mapper = mapper;
    }

    @Override
    public DomainMovie save(DomainMovie entity) {
        Movie movieEntity = mapper.toEntity(entity);
        Movie savedEntity = movieJpaRepository.save(movieEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainMovie> findById(Long id) {
        return movieJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainMovie> findAll() {
        return movieJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
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
