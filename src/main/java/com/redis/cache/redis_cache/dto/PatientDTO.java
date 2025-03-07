package com.redis.cache.redis_cache.dto;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private List<InsuranceClaimDTO> insuranceClaims; // Bidirectional mapping
}
