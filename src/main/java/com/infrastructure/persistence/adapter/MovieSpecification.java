package com.infrastructure.persistence.adapter;

import com.domain.models.DomainMovie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
    public static Specification<DomainMovie> hasTitle(String title) {
        return (Root<DomainMovie> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                title != null ? cb.like(root.get("title"), "%" + title + "%") : null;
    }

    public static Specification<DomainMovie> hasGenre(String genre) {
        return (Root<DomainMovie> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                genre != null ? cb.equal(root.get("genre"), genre) : null;
    }
}
