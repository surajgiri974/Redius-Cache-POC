package com.redis.cache.redis_cache.controller;

import com.redis.cache.redis_cache.entity.InsuranceClaim;
import com.redis.cache.redis_cache.service.InsuranceClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/claims")
public class InsuranceClaimController {

    private final InsuranceClaimService insuranceClaimService;

    public InsuranceClaimController(InsuranceClaimService insuranceClaimService) {
        this.insuranceClaimService = insuranceClaimService;
    }

    @PostMapping
    public ResponseEntity<InsuranceClaim> createClaim(@RequestBody InsuranceClaim claim) {
        return ResponseEntity.ok(insuranceClaimService.createClaim(claim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceClaim> getClaimById(@PathVariable Long id) {
        Optional<InsuranceClaim> claim = Optional.ofNullable(insuranceClaimService.getClaimById(id));
        return claim.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<InsuranceClaim> getAllClaims() {
        return insuranceClaimService.getAllClaims();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InsuranceClaim>> getClaimsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(insuranceClaimService.getClaimsByPatientId(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceClaim> updateClaim(@PathVariable Long id, @RequestBody InsuranceClaim claimDetails) {
        try {
            return ResponseEntity.ok(insuranceClaimService.updateClaim(id, claimDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        insuranceClaimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}
