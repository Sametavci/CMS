package com.infrastructure.api.mapper;

import com.domain.models.DomainMovie;
import com.infrastructure.api.dto.MovieDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("apiMovieMapper")
@NoArgsConstructor
public class MovieMapper implements BaseMapper<DomainMovie, MovieDTO> {
    private SessionMapper sessionMapper;

    @Override
    public MovieDTO toDto(DomainMovie entity) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setGenre(entity.getGenre());
        movieDTO.setDuration(entity.getDuration());
        movieDTO.setPrice(entity.getPrice());
        movieDTO.setSessionDTOS(entity.getSessions()
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList()));
        return movieDTO;
    }

    @Override
    public DomainMovie toEntity(MovieDTO dto) {
        DomainMovie movieEntity = new DomainMovie();
        movieEntity.setId(dto.getId());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setGenre(dto.getGenre());
        movieEntity.setDuration(dto.getDuration());
        movieEntity.setPrice(dto.getPrice());
        movieEntity.setSessions(dto.getSessionDTOS()
                .stream()
                .map(sessionMapper::toEntity)
                .collect(Collectors.toList()));
        return movieEntity;
    }
}
