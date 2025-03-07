package com.redis.cache.redis_cache.repository;

import com.redis.cache.redis_cache.entity.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    @EntityGraph(attributePaths = {"insuranceClaims"})  // ✅ Fetches related data in one query
    List<Patient> findAll();

    @EntityGraph(attributePaths = {"insuranceClaims"})  // ✅ Fetch claims eagerly
    Optional<Patient> findById(Long patientId);
}

