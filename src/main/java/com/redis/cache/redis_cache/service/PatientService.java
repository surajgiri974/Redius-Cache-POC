package com.redis.cache.redis_cache.service;

import com.redis.cache.redis_cache.entity.Patient;
import com.redis.cache.redis_cache.repository.PatientRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final CacheManager cacheManager;
    public PatientService(PatientRepository patientRepository, CacheManager cacheManager) {
        this.patientRepository = patientRepository;
        this.cacheManager = cacheManager;
    }

    @CachePut(value = "patient", key = "#patient.patientId") // Updates cache after saving
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Cacheable(value = "patient", key = "#patientId") // Caches result if not already cached
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }

    @Cacheable(value = "patients", key = "'allPatients'") // Caches list of all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @CachePut(value = "patient", key = "#patientId") // Updates individual patient cache
    public Patient updatePatient(Long patientId, Patient patientDetails) {
        return patientRepository.findById(patientId).map(existingPatient -> {
            existingPatient.setFirstName(patientDetails.getFirstName());
            existingPatient.setLastName(patientDetails.getLastName());
            existingPatient.setDateOfBirth(patientDetails.getDateOfBirth());
            existingPatient.setGender(patientDetails.getGender());
            existingPatient.setContactNumber(patientDetails.getContactNumber());
            existingPatient.setEmail(patientDetails.getEmail());
            existingPatient.setAddress(patientDetails.getAddress());

            Patient updatedPatient = patientRepository.save(existingPatient);

            // 🔹 Manually update the "patients" cache list
            Cache cache = cacheManager.getCache("patients");
            if (cache != null) {
                List<Patient> cachedPatients = cache.get("allPatients", List.class);
                if (cachedPatients != null) {
                    cachedPatients.removeIf(p -> p.getPatientId().equals(patientId)); // Remove old record
                    cachedPatients.add(updatedPatient); // Add updated record
                    cache.put("allPatients", cachedPatients); // Update cache
                }
            }

            return updatedPatient;
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @CacheEvict(value = "patient", key = "#patientId") // Removes patient from cache when deleted
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @CacheEvict(value = "patients", allEntries = true) // Clears all patients from cache when any modification occurs
    public void evictAllPatientsCache() {
    }

}

