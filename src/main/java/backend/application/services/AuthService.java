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
        return List.of();
    }

    @Override
    public Optional<TicketClerk> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public TicketClerk save(TicketClerk entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public TicketClerk update(TicketClerk dto, Long aLong) {
        return null;
    }

}
