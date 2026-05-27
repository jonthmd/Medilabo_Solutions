package com.medilabo.risk.dto;

import com.medilabo.risk.model.Risks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskDTO {

    private Risks level;
}
