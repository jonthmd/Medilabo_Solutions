package com.medilabo.front.service;

import com.medilabo.front.dto.PatientDTO;
import com.medilabo.front.dto.SavePatientDTO;

import java.util.List;

/**
 * Service interface managing operations related to front for patient.
 */
public interface PatientFrontService {

    List<PatientDTO> getAllPatients();
    List<PatientDTO> searchPatients(String lastName);
    SavePatientDTO addPatient(SavePatientDTO savePatientDTO);
    PatientDTO getPatientById(Long id);
    SavePatientDTO updatePatient(Long id, SavePatientDTO savePatientDTO) ;
    void deletePatient(Long id);
}
