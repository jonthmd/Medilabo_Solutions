package com.medilabo.risk.controller;

import com.medilabo.risk.service.RiskService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RiskController.class)
class RiskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RiskService riskService;

    @Test
    @WithMockUser
    void getRisk() throws Exception {

        String json = """
                {
                  "patient": {
                    "id": 4,
                    "firstName": "Jonathan",
                    "lastName": "TH",
                    "birthDate": "2002-06-28",
                    "gender": "F",
                    "address": "string",
                    "phone": "string"
                  },
                  "note": [
                    {
                      "id": "123",
                      "patientId": 4,
                      "note": "Le patient déclare quil lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments."
                    },
                    {
                      "id": "343",
                      "patientId": 3,
                      "note": "Le patient déclare quil a mal au dos lorsquil reste assis pendant longtemps."
                    },
                    {
                      "id": "373",
                      "patientId": 3,
                      "note": "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé."
                    },
                    {
                      "id": "393",
                      "patientId": 3,
                      "note": "Taille, Poids, Cholestérol, Vertige et Réaction."
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/risk/level")
                        .with(csrf())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk());
    }
}