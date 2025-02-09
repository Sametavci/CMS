package com.application.services;

import com.domain.models.DomainHall;
import com.domain.models.DomainSeat;
import com.domain.ports.repositorys.IHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HallService extends BaseService<DomainHall, Long> {
    private final IHallRepository hallRepository;

    public HallService(IHallRepository hallRepository) {
        super(hallRepository);
        this.hallRepository = hallRepository;
    }

    public boolean isAllSeatsFullByHallId(Long id) {
        DomainHall hall = hallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hall with ID " + id + " not found."));

        return hall.getSeats().stream().allMatch(DomainSeat::isBooked);
    }

    public List<DomainSeat> showEmptySeats(Long id) {
        DomainHall hall = hallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hall with ID " + id + " not found."));

        return hall.getSeats().stream()
                .filter(seat -> !seat.isBooked())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DomainHall> findById(Long id) {
        return hallRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        hallRepository.deleteById(id);
    }
}
