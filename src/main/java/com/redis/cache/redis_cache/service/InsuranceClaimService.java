package com.redis.cache.redis_cache.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import com.redis.cache.redis_cache.entity.InsuranceClaim;
import com.redis.cache.redis_cache.repository.InsuranceClaimRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InsuranceClaimService {

    private final InsuranceClaimRepository insuranceClaimRepository;
    private final CacheManager cacheManager;

    public InsuranceClaimService(InsuranceClaimRepository insuranceClaimRepository, CacheManager cacheManager) {
        this.insuranceClaimRepository = insuranceClaimRepository;
        this.cacheManager = cacheManager;
    }

    @CachePut(value = "insuranceClaim", key = "#claim.claimId") // Updates cache after saving
    public InsuranceClaim createClaim(InsuranceClaim claim) {
        return insuranceClaimRepository.save(claim);
    }

    @Cacheable(value = "insuranceClaim", key = "#claimId") // Caches result if not already cached
    public InsuranceClaim getClaimById(Long claimId) {
        return insuranceClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurance claim not found"));
    }

    @Cacheable(value = "allInsuranceClaims",key = "'allClaims'") // Caches all claims
    public List<InsuranceClaim> getAllClaims() {
        return insuranceClaimRepository.findAll();
    }

    @Cacheable(value = "insuranceClaimsByPatient", key = "#patientId") // Caches claims by patient
    public List<InsuranceClaim> getClaimsByPatientId(Long patientId) {
        return insuranceClaimRepository.findByPatientPatientId(patientId);
    }

    @CachePut(value = "insuranceClaim", key = "#claimId") // Updates cache after modifying the claim
    public InsuranceClaim updateClaim(Long claimId, InsuranceClaim claimDetails) {
        return insuranceClaimRepository.findById(claimId).map(existingClaim -> {
            if (claimDetails != null) {
                existingClaim.setClaimDate(claimDetails.getClaimDate());
                existingClaim.setStatus(claimDetails.getStatus());
                existingClaim.setAmount(claimDetails.getAmount());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Claim details cannot be null");
            }
            InsuranceClaim updatedClaim = insuranceClaimRepository.save(existingClaim);

            Cache cache = cacheManager.getCache("allInsuranceClaims");
            if (cache != null) {
                List<InsuranceClaim> cachedClaims = cache.get("allClaims", List.class);
                if (cachedClaims != null) {
                    cachedClaims.removeIf(c -> c.getClaimId().equals(claimId)); // Remove old record
                    cachedClaims.add(updatedClaim); // Add updated record
                    cache.put("allClaims", cachedClaims); // Update cache
                }
            }

            return updatedClaim;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurance claim not found"));
    }

    @CacheEvict(value = "insuranceClaim", key = "#claimId") // Removes claim from cache when deleted
    public void deleteClaim(Long claimId) {
        if (!insuranceClaimRepository.existsById(claimId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insurance claim not found");
        }
        insuranceClaimRepository.deleteById(claimId);
    }

    @CacheEvict(value = "allInsuranceClaims", allEntries = true) // Clears all claims from cache when any modification occurs
    public void evictAllClaimsCache() {
        // This method clears the entire claims list cache
    }
}
