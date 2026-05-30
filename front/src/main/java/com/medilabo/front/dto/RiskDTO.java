package com.medilabo.front.dto;

import com.medilabo.front.model.Risks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskDTO {

    private Risks level;
}
