package backend.domain.ports.repositorys;

import backend.infrastructure.persistence.entities.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedbackRepository extends BaseRepository<Feedback, Long>{

    public Long getCount();
}
