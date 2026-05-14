package com.medilabo.front.feign;

import com.medilabo.front.dto.PatientDTO;
import com.medilabo.front.dto.SavePatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient", url = "http://localhost:8080")
public interface PatientFeign {

    @GetMapping("/api/patient/all")
    List<PatientDTO> getAllPatients();

    @GetMapping("/api/patient/search")
    List<PatientDTO> searchPatients(@RequestParam String lastName);

    @GetMapping("/api/patient/{id}")
    PatientDTO getPatientById(@PathVariable Long id);

    @PostMapping("/api/patient/add")
    PatientDTO addPatient(@RequestBody SavePatientDTO savePatientDTO);

    @PutMapping("/api/patient/update/{id}")
    SavePatientDTO updatePatient(@PathVariable Long id, @RequestBody SavePatientDTO savePatientDTO) ;

    @DeleteMapping("/api/patient/delete/{id}")
    void deletePatient(@PathVariable Long id);
}
