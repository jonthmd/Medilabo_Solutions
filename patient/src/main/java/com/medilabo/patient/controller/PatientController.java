package com.medilabo.patient.controller;

import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.dto.PatientDTO;
import com.medilabo.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller with endpoints operations related to the front.
 */
@Slf4j
@RestController
@RequestMapping("/patient")
@Tag(name = "Patient", description = "Patients of Medilabo Solutions.")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves all patients.
     *
     * @return A list of PatientDTO representing all patients.
     */
    @GetMapping("/all")
    @Operation(summary = "Get all patients.")
    public List<PatientDTO> getAllPatients() {
        log.info("Start getAllPatients...");
        return patientService.findAll();
    }

    /**
     * Searches patients.
     *
     * @param lastName The last name of the patient.
     * @return A list of PatientDTO representing the result of the search.
     */
    @GetMapping("/search")
    @Operation(summary = "Search patients.")
    public List<PatientDTO> searchPatients(@RequestParam String lastName) {
        log.info("Start searchPatients...");
        return patientService.findByLastNameIgnoreCase(lastName);
    }

    /**
     * Creates a patient.
     *
     * @param savePatientDTO The information of the patient to create.
     * @return SavePatientDTO, a saved patient.
     */
    @PostMapping("/add")
    @Operation(summary = "Add a patient.")
    public SavePatientDTO addPatient(@RequestBody SavePatientDTO savePatientDTO) {
        log.info("Start addPatient...");
        return patientService.addPatient(savePatientDTO);
    }

    /**
     * Retrieves a patient via his/her id.
     *
     * @param id The id of the specified patient.
     * @return PatientDTO, a patient.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a patient by ID.")
    public PatientDTO getPatientById(@PathVariable Long id) {
        log.info("Start getPatientById...");
        return patientService.getPatientById(id);
    }

    /**
     * Updates an existing patient.
     *
     * @param id             The id of the specified patient.
     * @param savePatientDTO The information of the patient to update.
     * @return SavePatientDTO, a updated patient.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Edit a patient.")
    public SavePatientDTO updatePatient(@PathVariable Long id, @RequestBody SavePatientDTO savePatientDTO) {
        log.info("Start updatePatient...");
        return patientService.updatePatient(id, savePatientDTO);
    }

    /**
     * Deletes an existing patient.
     *
     * @param id The id of the specified patient.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a patient.")
    public void deletePatient(@PathVariable Long id) {
        log.info("Start deletePatient...");
        patientService.deletePatient(id);
    }
}
