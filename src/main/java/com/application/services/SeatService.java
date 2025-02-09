package com.application.services;

import com.domain.models.DomainSeat;
import com.domain.ports.repositorys.ISeatRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService extends BaseService<DomainSeat, Long> {
    private final ISeatRepository seatRepository;

    public SeatService(ISeatRepository seatRepository) {
        super(seatRepository);
        this.seatRepository = seatRepository;
    }

    @Override
    public Optional<DomainSeat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }
}
