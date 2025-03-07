package com.redis.cache.redis_cache;

import com.redis.cache.redis_cache.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PatientSpecification {

    public static Specification<Patient> getFacilitiesByFilters(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by Location only if it's not null or empty
            if (filters.containsKey("location")) {
                String location = (String) filters.get("location");
                if (location != null && !location.trim().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("location"), location));
                }
            }

            // Filter by Type only if it's not null or empty
            if (filters.containsKey("type")) {
                String type = (String) filters.get("type");
                if (type != null && !type.trim().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("type"), type));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
