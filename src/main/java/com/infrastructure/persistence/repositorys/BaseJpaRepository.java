package com.infrastructure.persistence.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID> {
}
