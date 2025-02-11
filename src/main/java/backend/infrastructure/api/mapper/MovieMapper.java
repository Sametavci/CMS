package backend.infrastructure.api.mapper;

import backend.domain.models.DomainMovie;
import backend.infrastructure.api.dto.MovieDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("apiMovieMapper")
@NoArgsConstructor
public class MovieMapper implements BaseMapper<DomainMovie, MovieDTO> {

    @Override
    public MovieDTO toDto(DomainMovie entity) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setGenre(entity.getGenre());
        movieDTO.setDuration(entity.getDuration());
        movieDTO.setPrice(entity.getPrice());
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
        return movieEntity;
    }
}
