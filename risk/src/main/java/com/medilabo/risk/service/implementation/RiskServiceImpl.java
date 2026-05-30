package com.medilabo.risk.service.implementation;

import com.medilabo.risk.dto.DetailsDTO;
import com.medilabo.risk.dto.NoteDTO;
import com.medilabo.risk.dto.PatientDTO;
import com.medilabo.risk.dto.RiskDTO;
import com.medilabo.risk.model.Risks;
import com.medilabo.risk.service.RiskService;
import com.medilabo.risk.utils.CalculateAge;
import com.medilabo.risk.utils.CountTriggers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RiskServiceImpl implements RiskService {

    private final CalculateAge calculateAge;
    private final CountTriggers countTriggers;

    public RiskServiceImpl(CalculateAge calculateAge, CountTriggers countTriggers) {
        this.calculateAge = calculateAge;
        this.countTriggers = countTriggers;
    }


    @Override
    public RiskDTO evaluation(DetailsDTO details) {

        PatientDTO patientDTO = details.getPatient();
        List<NoteDTO> noteDTOList = details.getNote();

        int age = calculateAge.calculate(String.valueOf(patientDTO.getBirthDate()));
        int count = countTriggers.count(noteDTOList);
        boolean m = patientDTO.getGender().equalsIgnoreCase("M");
        Risks risks;

        if (age > 30 && count >= 2 && count <= 5) {
            risks = Risks.BORDERLINE;
        } else if ((age < 30 && m && count == 3) || (age < 30 && !m && count == 4) || (age > 30 && count >= 6 && count <= 7)) {
            risks = Risks.DANGER;
        } else if ((age < 30 && m && count >= 5) || (age < 30 && !m && count >= 7) || (age > 30 && count >= 8)) {
            risks = Risks.ONSET;
        } else {
            risks = Risks.NONE;
        }

        log.info("Age : {}", age);
        log.info("Gender : {}", patientDTO.getGender());
        log.info("Triggers : {}", count);

        return new RiskDTO(risks);
    }
}
