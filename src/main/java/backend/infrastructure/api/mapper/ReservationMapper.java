package backend.infrastructure.api.mapper;


import backend.domain.models.DomainReservation;
import backend.infrastructure.api.dto.ReservationDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component("apiReservationMapper")
@NoArgsConstructor
public class ReservationMapper implements BaseMapper<DomainReservation, ReservationDTO>
{

    @Override
    public ReservationDTO toDto(DomainReservation reservationEntity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservationEntity.getId());
        dto.setCustomer(reservationEntity.getCustomer());
        dto.setSeats(reservationEntity.getSeat());
        dto.setSessions(reservationEntity.getSession());
        return dto;
    }

    @Override
    public DomainReservation toEntity(ReservationDTO reservationDTO) {
        DomainReservation entity = new DomainReservation();
        entity.setCustomer(reservationDTO.getCustomer());
        entity.setSeat(reservationDTO.getSeats());
        entity.setSession(reservationDTO.getSessions());
        return entity;
    }
}
