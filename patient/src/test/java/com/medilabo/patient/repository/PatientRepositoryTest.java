package com.medilabo.patient.repository;

import com.medilabo.patient.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void findByLastNameStartsWithIgnoreCase() {

        //GIVEN
        Patient patient = new Patient();
        patient.setLastName("TH");
        patientRepository.save(patient);

        //WHEN
        List<Patient> result = patientRepository.findByLastNameStartsWithIgnoreCase("TH");

        //THEN
        assertThat(result).hasSize(1);
    }
}