package backend.infrastructure.persistence.mapper;

import backend.infrastructure.persistence.entities.Movie;
import backend.domain.models.DomainMovie;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceMovieMapper")
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
        movie.setImgUrl(domain.getImgUrl());
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
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setImgUrl(entity.getImgUrl());
        return domain;
    }
    public Movie update(Movie movieEntity, DomainMovie dto) {
        movieEntity.setId(dto.getId());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setGenre(dto.getGenre());
        movieEntity.setDuration(dto.getDuration());
        movieEntity.setPrice(dto.getPrice());
        movieEntity.setUpdatedAt(LocalDateTime.now());
        movieEntity.setImgUrl(dto.getImgUrl());
        return movieEntity;
    }


}
