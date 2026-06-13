package com.medilabo.front.service.implementation;

import com.medilabo.front.dto.DetailsDTO;
import com.medilabo.front.dto.NoteDTO;
import com.medilabo.front.dto.PatientDTO;
import com.medilabo.front.dto.RiskDTO;
import com.medilabo.front.feign.NoteFeign;
import com.medilabo.front.feign.PatientFeign;
import com.medilabo.front.feign.RiskFeign;
import com.medilabo.front.service.RiskFrontService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the risk front service interface.
 */
@Service
public class RiskFrontServiceImpl implements RiskFrontService {

    private final PatientFeign patientFeign;
    private final NoteFeign noteFeign;
    private final RiskFeign riskFeign;

    public RiskFrontServiceImpl(PatientFeign patientFeign, NoteFeign noteFeign, RiskFeign riskFeign) {
        this.patientFeign = patientFeign;
        this.noteFeign = noteFeign;
        this.riskFeign = riskFeign;
    }

    /**
     * Retrieves a risk evaluation.
     *
     * @param patientId The specified patient id.
     * @return RiskDTO, a risk.
     */
    @Override
    public RiskDTO getRisk(Long patientId) {
        PatientDTO patient = patientFeign.getPatientById(patientId);
        List<NoteDTO> notes = noteFeign.findNoteByPatientId(patientId);

        DetailsDTO detailsDTO = new DetailsDTO(patient, notes);

        return riskFeign.getRisk(detailsDTO);
    }
}
