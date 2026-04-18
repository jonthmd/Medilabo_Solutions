package com.medilabo.patient.controller;

import com.medilabo.patient.service.PatientService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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

        mockMvc.perform(get("/patient/all"))
                .andExpect(status().isOk());
    }

    @Test
    void searchPatients() throws Exception {

        mockMvc.perform(get("/patient/search").param("lastName", "TestNone"))
                .andExpect(status().isOk());
    }

    @Test
    void addPatient() throws Exception {

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

        mockMvc.perform(post("/patient/add")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById() throws Exception {

        mockMvc.perform(get("/patient/{id}", "1"))
                .andExpect(status().isOk());

    }

    @Test
    void updatePatient() throws Exception {

        String json = """
                {
                  "firstName": "Jon"
                }
                """;

        mockMvc.perform(put("/patient/{id}", "1")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatient() throws Exception {

        mockMvc.perform(delete("/patient/{id}", "1"))
                .andExpect(status().isOk());
    }
}