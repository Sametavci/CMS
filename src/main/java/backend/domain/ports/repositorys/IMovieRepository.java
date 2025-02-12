package backend.domain.ports.repositorys;

import backend.domain.models.DomainMovie;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IMovieRepository extends BaseRepository<DomainMovie, Long>{

}
