package backend.domain.ports.repositorys;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


public interface BaseRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
    T update(T dto, ID id);
}
