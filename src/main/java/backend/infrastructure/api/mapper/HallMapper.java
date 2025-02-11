package backend.infrastructure.api.mapper;

import backend.domain.models.DomainHall;
import backend.infrastructure.api.dto.HallDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component("apiHallMapper")
@NoArgsConstructor
public class HallMapper implements BaseMapper<DomainHall, HallDTO> {

    @Override
    public HallDTO toDto(DomainHall hallEntity) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(hallEntity.getId());
        hallDTO.setName(hallEntity.getName());
        hallDTO.setCapacity(hallEntity.getCapacity());
        hallDTO.setType(hallEntity.getType());
        return hallDTO;
    }

    @Override
    public DomainHall toEntity(HallDTO dto) {
        DomainHall hallEntity = new DomainHall();
        hallEntity.setName(dto.getName());
        hallEntity.setType(dto.getType());
        hallEntity.setUpdatedAt(LocalDateTime.now());
        return hallEntity;
    }

}
