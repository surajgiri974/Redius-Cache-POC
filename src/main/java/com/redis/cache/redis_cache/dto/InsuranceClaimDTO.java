package com.redis.cache.redis_cache.dto;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceClaimDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long claimId;
    private String claimType;
    private double amount;
    private String status;
    private LocalDate claimDate;
    private PatientDTO patient; // Full Patient DTO
}
