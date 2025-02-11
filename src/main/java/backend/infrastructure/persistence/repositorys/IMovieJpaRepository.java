package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieJpaRepository extends BaseJpaRepository<Movie, Long> {
Movie findByTitle(String title);
}
