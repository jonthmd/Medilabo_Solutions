package com.medilabo.patient.repository;

import com.medilabo.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing {@link Patient}.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByLastNameContainingIgnoreCase(String lastName);
}
