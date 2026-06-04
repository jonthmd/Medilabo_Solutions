package com.medilabo.front.feign;

import com.medilabo.front.dto.DetailsDTO;
import com.medilabo.front.dto.RiskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "risk", url = "${gateway.base-url}")
public interface RiskFeign {

    @PostMapping("/api/risk/level")
    RiskDTO getRisk(@RequestBody DetailsDTO detailsDTO);
}
