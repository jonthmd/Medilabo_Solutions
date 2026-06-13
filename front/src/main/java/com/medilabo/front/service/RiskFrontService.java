package com.medilabo.front.service;

import com.medilabo.front.dto.RiskDTO;

/**
 * Service interface managing operations related to front for risk.
 */
public interface RiskFrontService {

    RiskDTO getRisk(Long patientId);
}
