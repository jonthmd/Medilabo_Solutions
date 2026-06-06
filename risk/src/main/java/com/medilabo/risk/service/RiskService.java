package com.medilabo.risk.service;

import com.medilabo.risk.dto.DetailsDTO;
import com.medilabo.risk.dto.RiskDTO;

/**
 * Service interface managing operations related to risk.
 */
public interface RiskService {

    RiskDTO evaluation(DetailsDTO detailsDTO);
}
