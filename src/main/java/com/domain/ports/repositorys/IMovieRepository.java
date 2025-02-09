package com.domain.ports.repositorys;

import com.domain.models.DomainMovie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends BaseRepository<DomainMovie, Long>{


List<DomainMovie> getMoviesByFilter(String title, String genre);
}
