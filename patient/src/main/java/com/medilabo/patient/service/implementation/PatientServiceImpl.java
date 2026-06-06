package com.medilabo.patient.service.implementation;

import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.dto.PatientDTO;
import com.medilabo.patient.exception.PatientNotFoundException;
import com.medilabo.patient.mapper.SavePatientMapper;
import com.medilabo.patient.mapper.PatientMapper;
import com.medilabo.patient.model.Patient;
import com.medilabo.patient.repository.PatientRepository;
import com.medilabo.patient.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the patient service interface.
 */
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final SavePatientMapper savePatientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, SavePatientMapper savePatientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.savePatientMapper = savePatientMapper;
    }

    /**
     * Retrieves a list of patients.
     *
     * @return A list of PatientDTO.
     */
    @Override
    public List<PatientDTO> findAll() {

        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientDTO)
                .toList();
    }

    /**
     * Retrieves a list of patient via his/her lastname.
     *
     * @param lastName The last name of the patient.
     * @return A list of PatientDTO.
     */
    @Override
    public List<PatientDTO> findByLastNameIgnoreCase(String lastName) {

        return patientRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(patientMapper::patientToPatientDTO)
                .toList();
    }

    /**
     * Creates a patient.
     *
     * @param savePatientDTO Mapped object containing the patient to be created.
     * @return SavePatientDTO, the created patient.
     */
    @Override
    public SavePatientDTO addPatient(SavePatientDTO savePatientDTO) {

        Patient patient = savePatientMapper.savePatientDTOToPatient(savePatientDTO);
        Patient saved = patientRepository.save(patient);

        return savePatientMapper.patientToSavePatientDTO(saved);
    }

    /**
     * Retrieves a patient via his/her id.
     *
     * @param id The specified patient id.
     * @return PatientDTO, the retrieved patient.
     */
    @Override
    public PatientDTO getPatientById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        return patientMapper.patientToPatientDTO(patient);
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

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        existingPatient.setFirstName(savePatientDTO.getFirstName());
        existingPatient.setLastName(savePatientDTO.getLastName());

        if (savePatientDTO.getBirthDate() != null) {
            existingPatient.setBirthDate(savePatientDTO.getBirthDate());
        }

        existingPatient.setGender(savePatientDTO.getGender());
        existingPatient.setAddress(savePatientDTO.getAddress());
        existingPatient.setPhone(savePatientDTO.getPhone());
        Patient updatedPatient = patientRepository.save(existingPatient);

        return savePatientMapper.patientToSavePatientDTO(updatedPatient);
    }

    /**
     * Deletes an existing patient.
     *
     * @param id The specified patient id.
     */
    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        patientRepository.delete(patient);
    }
}
