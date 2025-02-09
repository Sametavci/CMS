package com.infrastructure.api.mapper;

import com.domain.models.DomainHall;
import com.infrastructure.api.dto.HallDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component("apiHallMapper")
@NoArgsConstructor
public class HallMapper implements BaseMapper<DomainHall, HallDTO> {

    private SeatMapper seatMapper;
    private SessionMapper sessionMapper;

    @Override
    public HallDTO toDto(DomainHall hallEntity) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(hallEntity.getId());
        hallDTO.setName(hallEntity.getName());
        hallDTO.setCapacity(hallEntity.getCapacity());
        hallDTO.setType(hallEntity.getType());
        hallDTO.setSessionDTOS(hallEntity.getSessions()
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList()));
        hallDTO.setSeatDTOS(hallEntity.getSeats()
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList()));
        return hallDTO;
    }

    @Override
    public DomainHall toEntity(HallDTO dto) {
        DomainHall hallEntity = new DomainHall();
        hallEntity.setName(dto.getName());
        hallEntity.setType(dto.getType());
        hallEntity.setUpdatedAt(LocalDateTime.now());
        hallEntity.setSessions(dto.getSessionDTOS()
                .stream()
                .map(sessionMapper::toEntity)
                .collect(Collectors.toList()));
        hallEntity.setSeats(dto.getSeatDTOS()
                .stream()
                .map(seatMapper::toEntity)
                .collect(Collectors.toList()));
        return hallEntity;
    }

}
