package com.medilabo.patient.controller;

import com.medilabo.patient.dto.PatientDTO;
import com.medilabo.patient.dto.SavePatientDTO;
import com.medilabo.patient.service.PatientService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @Test
    void getAllPatients() throws Exception {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setFirstName("Jon");

        when(patientService.findAll()).thenReturn(List.of(patientDTO));

        //WHEN+THEN
        mockMvc.perform(get("/patient/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jon"));

        verify(patientService).findAll();
    }

    @Test
    void searchPatients() throws Exception {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setLastName("TH");

        when(patientService.findByLastNameIgnoreCase("TH")).thenReturn(List.of(patientDTO));

        //WHEN+THEN
        mockMvc.perform(get("/patient/search").param("lastName", "TH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("TH"));

        verify(patientService).findByLastNameIgnoreCase("TH");
    }

    @Test
    void addPatient() throws Exception {

        //GIVEN
        String json = """
                {
                  "firstName": "Jon",
                  "lastName": "TH",
                  "birthDate": "2026",
                  "gender": "M",
                  "address": "5 PE",
                  "phone": "999"
                }
                """;

        SavePatientDTO savePatientDTO = new SavePatientDTO();
        savePatientDTO.setFirstName("Jon");

        when(patientService.addPatient(any(SavePatientDTO.class))).thenReturn(savePatientDTO);

        //WHEN+THEN
        mockMvc.perform(post("/patient/add")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jon"));
    }

    @Test
    void getPatientById() throws Exception {

        //GIVEN
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setFirstName("Jon");

        when(patientService.getPatientById(1L)).thenReturn(patientDTO);

        //WHEN+THEN
        mockMvc.perform(get("/patient/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jon"))
                .andExpect(jsonPath("$.id").value(1L));

        verify(patientService).getPatientById(1L);
    }

    @Test
    void updatePatient() throws Exception {

        //GIVEN
        String json = """
                {
                  "firstName": "Jon"
                }
                """;

        SavePatientDTO savePatientDTO = new SavePatientDTO();
        savePatientDTO.setFirstName("Jon");

        when(patientService.updatePatient(eq(1L), any(SavePatientDTO.class))).thenReturn(savePatientDTO);

        //WHEN+THEN
        mockMvc.perform(put("/patient/update/{id}", 1L)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Jon"));

        verify(patientService).updatePatient(eq(1L), any(SavePatientDTO.class));
    }

    @Test
    void deletePatient() throws Exception {

        mockMvc.perform(delete("/patient/delete/{id}", 1L))
                .andExpect(status().isOk());

        verify(patientService).deletePatient(1L);
    }
}