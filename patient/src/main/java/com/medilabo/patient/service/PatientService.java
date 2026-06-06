package com.medilabo.patient.service;

import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.dto.PatientDTO;

import java.util.List;

/**
 * Service interface managing operations related to patient.
 */
public interface PatientService {

    List<PatientDTO> findAll();
    List<PatientDTO> findByLastNameIgnoreCase(String lastName);
    SavePatientDTO addPatient(SavePatientDTO savePatientDTO);
    PatientDTO getPatientById(Long id);
    SavePatientDTO updatePatient(Long id, SavePatientDTO savePatientDTO);
    void deletePatient(Long id);
}
