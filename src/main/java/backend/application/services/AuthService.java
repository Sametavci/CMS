package backend.application.services;

import backend.domain.models.DomainTicketClerk;
import backend.domain.ports.repositorys.BaseRepository;
import backend.domain.ports.repositorys.ITicketClerkRepository;
import backend.infrastructure.persistence.entities.TicketClerk;
import backend.infrastructure.persistence.mapper.TicketClerkMapper;
import backend.infrastructure.persistence.repositorys.ITickerClerkJpaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements ITicketClerkRepository{
    private final ITickerClerkJpaRepository jpaRepository;
    @Override
    public Optional<TicketClerk> findByUsername(String username) {
        return jpaRepository.findByEmail(username);
    }


    @Override
    public List<TicketClerk> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<TicketClerk> findById(Long aLong) {
        return jpaRepository.findById(aLong);
    }

    @Override
    public TicketClerk save(TicketClerk entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        jpaRepository.deleteById(aLong);
    }

    @Override
    public TicketClerk update(TicketClerk dto, Long aLong) {
        return jpaRepository.save(dto);
    }

}
