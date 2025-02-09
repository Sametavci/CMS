package com.infrastructure.persistence.adapter;

import com.domain.models.DomainSeat;
import com.domain.ports.repositorys.ISeatRepository;
import com.infrastructure.persistence.entities.Seat;
import com.infrastructure.persistence.mapper.SeatMapper;
import com.infrastructure.persistence.repositorys.ISeatJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatRepositoryAdapter implements ISeatRepository {

    private final ISeatJpaRepository seatJpaRepository;
    private final SeatMapper mapper;

    @Autowired
    public SeatRepositoryAdapter(ISeatJpaRepository seatJpaRepository, SeatMapper mapper) {
        this.seatJpaRepository = seatJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainSeat save(DomainSeat entity) {
        Seat seatEntity = mapper.toEntity(entity);
        Seat savedEntity = seatJpaRepository.save(seatEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainSeat> findById(Long id) {
        return seatJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainSeat> findAll() {
        return seatJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        seatJpaRepository.deleteById(id);
    }
}
