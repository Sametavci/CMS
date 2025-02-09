package com.infrastructure.api.mapper;

import org.springframework.stereotype.Component;

@Component
public interface BaseMapper<T, D> {
     D toDto(T entity);

     T toEntity(D dto);

}