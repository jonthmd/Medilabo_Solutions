package com.medilabo.patient.mapper;

import com.medilabo.patient.dto.PatientDTO;
import com.medilabo.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO patientToPatientDTO(Patient patient);
}
