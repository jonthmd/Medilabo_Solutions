package com.medilabo.risk.controller;

import com.medilabo.risk.dto.DetailsDTO;
import com.medilabo.risk.dto.RiskDTO;
import com.medilabo.risk.service.RiskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Controller with endpoint operation related to the risk.
 */
@Slf4j
@RestController
@RequestMapping("/risk")
@Tag(name="Risk", description="Risk Evaluation of a patient.")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    /**
     * Retrieves the risk of a patient by counting triggers.
     * @param detailsDTO Patient and notes details.
     * @return RiskDTO, a risk evaluation.
     */
    @PostMapping("/level")
    @Operation(summary = "Get risk of the patient.")
    public RiskDTO getRisk(@RequestBody DetailsDTO detailsDTO) {
        log.info("Start getRisk...");
        return riskService.evaluation(detailsDTO);
    }
}
