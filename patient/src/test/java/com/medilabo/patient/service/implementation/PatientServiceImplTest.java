package com.medilabo.patient.service.implementation;

import com.medilabo.patient.dto.PatientDTO;
import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.mapper.PatientMapper;
import com.medilabo.patient.mapper.SavePatientMapper;
import com.medilabo.patient.model.Patient;
import com.medilabo.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @Mock
    private SavePatientMapper savePatientMapper;

    @InjectMocks
    private PatientServiceImpl classUnderTest;

    @Test
    void findAll() {

        //GIVEN
        Patient patient = new Patient();
        List<Patient> patientList = List.of(patient);

        PatientDTO patientDTO = new PatientDTO();
        List<PatientDTO> patientDTOList = List.of(patientDTO);

        when(patientRepository.findAll()).thenReturn(patientList);
        when(patientMapper.patientToPatientDTO(patient)).thenReturn(patientDTO);

        //WHEN
        List<PatientDTO> result = classUnderTest.findAll();

        //THEN
        verify(patientRepository).findAll();
        verify(patientMapper).patientToPatientDTO(patient);
        assertThat(result).isEqualTo(patientDTOList);
    }

    @Test
    void findByLastNameIgnoreCase() {

        //GIVEN
        Patient patient = new Patient();
        patient.setLastName("TH");
        List<Patient> patientList = List.of(patient);

        PatientDTO patientDTO = new PatientDTO();
        List<PatientDTO> patientDTOList = List.of(patientDTO);

        when(patientRepository.findByLastNameStartsWithIgnoreCase("TH")).thenReturn(patientList);
        when(patientMapper.patientToPatientDTO(patient)).thenReturn(patientDTO);

        //WHEN
        List<PatientDTO> result = classUnderTest.findByLastNameIgnoreCase("TH");

        //THEN
        verify(patientRepository).findByLastNameStartsWithIgnoreCase("TH");
        verify(patientMapper).patientToPatientDTO(patient);
        assertThat(result).isEqualTo(patientDTOList);
    }

    @Test
    void addPatient() {

        //GIVEN
        Patient patient = new Patient();
        SavePatientDTO savePatientDTO = new SavePatientDTO();

        when(savePatientMapper.savePatientDTOToPatient(savePatientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(savePatientMapper.patientToSavePatientDTO(patient)).thenReturn(savePatientDTO);

        //WHEN
        SavePatientDTO result = classUnderTest.addPatient(savePatientDTO);

        //THEN
        verify(savePatientMapper).savePatientDTOToPatient(savePatientDTO);
        verify(patientRepository).save(patient);
        assertThat(result).isEqualTo(savePatientDTO);
    }

    @Test
    void getPatientById() {

        //GIVEN
        Patient patient = new Patient();
        PatientDTO patientDTO = new PatientDTO();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.patientToPatientDTO(patient)).thenReturn(patientDTO);

        //WHEN
        PatientDTO result = classUnderTest.getPatientById(1L);

        //THEN
        verify(patientRepository).findById(1L);
        verify(patientMapper).patientToPatientDTO(patient);
        assertThat(result).isEqualTo(patientDTO);
    }

    @Test
    void getPatientByIdPatientNotFound() {

        //GIVEN
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getPatientById(1L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updatePatient() {

        //GIVEN
        Patient patient = new Patient();
        SavePatientDTO savePatientDTO = new SavePatientDTO();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        when(savePatientMapper.patientToSavePatientDTO(patient)).thenReturn(savePatientDTO);

        //WHEN
        SavePatientDTO result = classUnderTest.updatePatient(1L, savePatientDTO);

        //THEN
        verify(patientRepository).findById(1L);
        verify(savePatientMapper).patientToSavePatientDTO(patient);
        verify(patientRepository).save(patient);
        assertThat(result).isEqualTo(savePatientDTO);
    }

    @Test
    void updatePatientPatientNotFound() {

        //GIVEN
        SavePatientDTO savePatientDTO = new SavePatientDTO();
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.updatePatient(1L, savePatientDTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deletePatient() {

        //GIVEN
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        //WHEN
        classUnderTest.deletePatient(1L);

        //THEN
        verify(patientRepository).findById(1L);
    }

    @Test
    void deletePatientPatientNotFound() {

        //GIVEN
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deletePatient(1L)).isInstanceOf(RuntimeException.class);
    }
}