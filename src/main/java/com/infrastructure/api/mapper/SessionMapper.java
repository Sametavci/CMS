package com.infrastructure.api.mapper;


import com.domain.models.DomainSession;
import com.infrastructure.api.dto.SessionDTO;
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
        dto.setMovieDTO(movieMapper.toDto(sessionEntity.getMovie()));
        dto.setHallDTO(hallMapper.toDto(sessionEntity.getHall()));
        dto.setPrice(dto.getPrice());
        return dto;
    }

    public DomainSession toEntity(SessionDTO sessionDTO)
    {
        DomainSession entity = new DomainSession();
        entity.setId(sessionDTO.getId());
        entity.setStartTime(LocalDateTime.now()); // String -> LocalDateTime
        entity.setPrice(sessionDTO.getPrice());
        entity.setMovie(movieMapper.toEntity(sessionDTO.getMovieDTO()));
        entity.setHall(hallMapper.toEntity(sessionDTO.getHallDTO()));
        return entity;
    }
}
