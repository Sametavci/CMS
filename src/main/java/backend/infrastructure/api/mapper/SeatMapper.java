package backend.infrastructure.api.mapper;


import backend.domain.models.DomainSeat;
import backend.infrastructure.api.dto.SeatDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component("apiSeatMapper")
@NoArgsConstructor
public class SeatMapper implements BaseMapper<DomainSeat, SeatDTO> {


    @Override
    public SeatDTO toDto(DomainSeat seatEntity)
    {
        SeatDTO dto = new SeatDTO();
        dto.setId(seatEntity.getId());
        dto.setSeatRow(seatEntity.getSeatRow());
        dto.setSeatColumn(seatEntity.getSeatColumn());
        dto.setBooked(seatEntity.isBooked());
        dto.setHallDTO(seatEntity.getHall());
        return dto;
    }

    @Override
    public DomainSeat toEntity(SeatDTO seatDTO)
    {
        DomainSeat entity = new DomainSeat();
        entity.setSeatRow(entity.getSeatRow());
        entity.setSeatColumn(entity.getSeatColumn());
        entity.setBooked(entity.isBooked());
        entity.setHall(seatDTO.getHallDTO());
        return entity;
    }
}
