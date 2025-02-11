package backend.infrastructure.api.mapper;


import backend.domain.models.DomainSession;
import backend.infrastructure.api.dto.SessionDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("apiSessionMapper")
@NoArgsConstructor
public class SessionMapper implements BaseMapper<DomainSession, SessionDTO>
{
    private HallMapper hallMapper;
    private MovieMapper movieMapper;


    public SessionDTO toDto(DomainSession sessionEntity)
    {
        SessionDTO dto = new SessionDTO();
        dto.setId(sessionEntity.getId());
        dto.setTime(sessionEntity.getStartTime());
        dto.setMovieDTO(sessionEntity.getMovie());
        dto.setPrice(sessionEntity.getPrice());
        dto.setHallDTO(sessionEntity.getHall());
        dto.setPrice(dto.getPrice());
        return dto;
    }

    public DomainSession toEntity(SessionDTO sessionDTO)
    {
        DomainSession entity = new DomainSession();
        entity.setId(sessionDTO.getId());
        entity.setStartTime(LocalDateTime.now());
        entity.setPrice(sessionDTO.getPrice());
        entity.setMovie(sessionDTO.getMovieDTO());
        entity.setHall(sessionDTO.getHallDTO());
        return entity;
    }
}
