package backend.application.services;

import backend.domain.models.DomainFeedback;
import backend.domain.ports.repositorys.IFeedbackRepository;
import backend.infrastructure.persistence.entities.Feedback;
import backend.infrastructure.persistence.repositorys.IFeedbackJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackRepository {

    private final IFeedbackJpaRepository repository;

    @Override
    public List<Feedback> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Feedback> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public Feedback save(Feedback entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public Feedback update(Feedback dto, Long aLong) {
        return repository.save(dto);
    }

    @Override
    public Long getCount() {
        return repository.count();
    }
}
