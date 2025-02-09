package com.infrastructure.persistence.mapper;

import com.domain.models.DomainMovie;
import com.infrastructure.persistence.entities.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements BaseMapper<Movie, DomainMovie> {

    @Override
    public Movie toEntity(DomainMovie domain) {
        if (domain == null) return null;
        Movie movie = new Movie();
        movie.setId(domain.getId());
        movie.setTitle(domain.getTitle());
        movie.setGenre(domain.getGenre());
        movie.setDuration(domain.getDuration());
        movie.setPrice(domain.getPrice());
        return movie;
    }

    @Override
    public DomainMovie toDomain(Movie entity) {
        if (entity == null) return null;
        DomainMovie domain = new DomainMovie();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setGenre(entity.getGenre());
        domain.setDuration(entity.getDuration());
        domain.setPrice(entity.getPrice());
        return domain;
    }

}
