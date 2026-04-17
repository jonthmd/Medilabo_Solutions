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

    @Override
    public List<PatientDTO> findAll() {

        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientDTO)
                .toList();
    }

    @Override
    public List<PatientDTO> findByLastNameIgnoreCase(String lastName){

        List<Patient> patients = patientRepository.findByLastNameStartsWithIgnoreCase(lastName);

        return patients.stream()
                .map(patientMapper::patientToPatientDTO)
                .toList();
    }

    @Override
    public SavePatientDTO addPatient(SavePatientDTO savePatientDTO) {

        Patient patient = savePatientMapper.savePatientDTOToPatient(savePatientDTO);
        Patient saved = patientRepository.save(patient);

        return savePatientMapper.patientToSavePatientDTO(saved);
    }

    @Override
    public PatientDTO getPatientById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        return patientMapper.patientToPatientDTO(patient);
    }

    @Override
    public SavePatientDTO updatePatient(Long id, SavePatientDTO savePatientDTO) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        patient.setFirstName(savePatientDTO.getFirstName());
        patient.setLastName(savePatientDTO.getLastName());
        patient.setBirthDate(savePatientDTO.getBirthDate());
        patient.setGender(savePatientDTO.getGender());
        patient.setAddress(savePatientDTO.getAddress());
        patient.setPhone(savePatientDTO.getPhone());
        Patient updatedPatient = patientRepository.save(patient);

        return savePatientMapper.patientToSavePatientDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        patientRepository.delete(patient);
    }
}
