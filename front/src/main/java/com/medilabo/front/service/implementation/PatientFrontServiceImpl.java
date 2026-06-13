package com.medilabo.front.service.implementation;

import com.medilabo.front.dto.PatientDTO;
import com.medilabo.front.dto.SavePatientDTO;
import com.medilabo.front.feign.PatientFeign;
import com.medilabo.front.service.PatientFrontService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the patient front service interface.
 */
@Service
public class PatientFrontServiceImpl implements PatientFrontService {

    private final PatientFeign patientFeign;

    public PatientFrontServiceImpl(PatientFeign patientFeign) {
        this.patientFeign = patientFeign;
    }

    /**
     * Retrieves a list of patients.
     *
     * @return A list of PatientDTO.
     */
    @Override
    public List<PatientDTO> getAllPatients() {
        return patientFeign.getAllPatients();
    }

    /**
     * Retrieves a list of patient via his/her lastname.
     *
     * @param lastName The last name of the patient.
     * @return A list of PatientDTO.
     */
    @Override
    public List<PatientDTO> searchPatients(String lastName) {
        return patientFeign.searchPatients(lastName);
    }

    /**
     * Creates a patient.
     *
     * @param savePatientDTO Mapped object containing the patient to be created.
     * @return SavePatientDTO, the created patient.
     */
    @Override
    public SavePatientDTO addPatient(SavePatientDTO savePatientDTO) {
        return patientFeign.addPatient(savePatientDTO);
    }

    /**
     * Retrieves a patient via his/her id.
     *
     * @param id The specified patient id.
     * @return PatientDTO, the retrieved patient.
     */
    @Override
    public PatientDTO getPatientById(Long id) {
        return patientFeign.getPatientById(id);
    }

    /**
     * Updates an existing patient.
     *
     * @param id             The specified patient id.
     * @param savePatientDTO Mapped object containing the patient to be updated.
     * @return SavePatientDTO, the updated patient.
     */
    @Override
    public SavePatientDTO updatePatient(Long id, SavePatientDTO savePatientDTO) {
        return patientFeign.updatePatient(id, savePatientDTO);
    }

    /**
     * Deletes an existing patient.
     *
     * @param id The specified patient id.
     */
    @Override
    public void deletePatient(Long id) {
        patientFeign.deletePatient(id);
    }
}
