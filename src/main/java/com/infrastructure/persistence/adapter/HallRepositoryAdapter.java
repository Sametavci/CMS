package com.infrastructure.persistence.adapter;

import com.domain.models.DomainHall;
import com.domain.models.DomainSeat;
import com.domain.ports.repositorys.IHallRepository;
import com.infrastructure.persistence.entities.Hall;
import com.infrastructure.persistence.mapper.HallMapper;
import com.infrastructure.persistence.repositorys.IHallJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HallRepositoryAdapter implements IHallRepository {

    private final IHallJpaRepository hallJpaRepository;
    private final HallMapper mapper;

    @Autowired
    public HallRepositoryAdapter(IHallJpaRepository hallJpaRepository, HallMapper mapper) {
        this.hallJpaRepository = hallJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainHall save(DomainHall entity) {
        Hall hallEntity = mapper.toEntity(entity);
        Hall savedEntity = hallJpaRepository.save(hallEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainHall> findById(Long id) {
        return hallJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainHall> findAll() {
        return hallJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        hallJpaRepository.deleteById(id);
    }

    @Override
    public boolean isAllSeatsFullByHallId(Long id) {
        return false;
    }

    @Override
    public List<DomainSeat> showEmptySeats(Long id) {
        return null;
    }
}
