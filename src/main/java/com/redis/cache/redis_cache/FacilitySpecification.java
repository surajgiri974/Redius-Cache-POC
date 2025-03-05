package com.redis.cache.redis_cache;

import org.springframework.data.jpa.domain.Specification;
import com.redis.cache.redis_cache.entity.Facility;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacilitySpecification {

    public static Specification<Facility> getFacilitiesByFilters(Map<String, Object> filters) {
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
