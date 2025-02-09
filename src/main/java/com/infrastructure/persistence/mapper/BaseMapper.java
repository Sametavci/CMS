package com.infrastructure.persistence.mapper;

public interface BaseMapper<T, D> {
    D toDomain(T entity);
    T toEntity(D domain);
}
