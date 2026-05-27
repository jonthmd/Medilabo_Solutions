package com.medilabo.risk.service;

import com.medilabo.risk.dto.DetailsDTO;
import com.medilabo.risk.dto.RiskDTO;

public interface RiskService {

    RiskDTO evaluation(DetailsDTO detailsDTO);
}
