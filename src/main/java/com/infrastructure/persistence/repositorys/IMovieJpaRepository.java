package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieJpaRepository extends BaseJpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
Movie findByTitle(String title);
}
