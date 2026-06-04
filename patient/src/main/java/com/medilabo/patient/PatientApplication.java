package com.medilabo.patient;

import com.medilabo.patient.model.Patient;
import com.medilabo.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {

	@Autowired
    PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... args) {

		if (patientRepository.count() == 0) {
			List<Patient> patients = List.of(
					new Patient(null, "Test", "TestNone", "1966-12-31", "F", "1 Brookside St", "100-222-3333"),
					new Patient(null, "Test", "TestBorderline", "1945-06-24", "M", "2 High St", "200-333-4444"),
					new Patient(null, "Test", "TestInDanger", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
					new Patient(null, "Test", "TestEarlyOnset", "2002-06-28", "F", "4 Valley Dr", "400-555-6666")
			);
			patientRepository.saveAll(patients);
		}
	}
}
