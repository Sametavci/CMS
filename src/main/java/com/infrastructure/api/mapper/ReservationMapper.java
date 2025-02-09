package com.infrastructure.api.mapper;


import com.domain.models.DomainReservation;
import com.infrastructure.api.dto.ReservationDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReservationMapper implements BaseMapper<DomainReservation, ReservationDTO>
{
    private CustomerMapper customerMapper;
    private SeatMapper seatMapper;
    private SessionMapper sessionMapper;

    @Override
    public ReservationDTO toDto(DomainReservation reservationEntity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservationEntity.getId());
        dto.setCustomer(customerMapper.toDto(reservationEntity.getCustomer()));
        dto.setSeats(seatMapper.toDto(reservationEntity.getSeat()));
        dto.setSessions(sessionMapper.toDto(reservationEntity.getSession()));
        return dto;
    }

    @Override
    public DomainReservation toEntity(ReservationDTO reservationDTO) {
        DomainReservation entity = new DomainReservation();
        entity.setCustomer(customerMapper.toEntity(reservationDTO.getCustomer()));
        entity.setSeat(seatMapper.toEntity(reservationDTO.getSeats()));
        entity.setSession(sessionMapper.toEntity(reservationDTO.getSessions()));
        return entity;
    }
}
