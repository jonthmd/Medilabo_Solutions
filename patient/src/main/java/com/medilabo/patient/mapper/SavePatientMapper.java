package com.medilabo.patient.mapper;

import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SavePatientMapper {

    SavePatientDTO patientToSavePatientDTO(Patient patient);
    Patient savePatientDTOToPatient(SavePatientDTO savePatientDTO);
}
