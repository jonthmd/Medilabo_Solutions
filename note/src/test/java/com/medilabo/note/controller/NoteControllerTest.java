package com.medilabo.note.controller;

import com.medilabo.note.repository.NoteRepository;
import com.medilabo.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteRepository noteRepository;

    @MockitoBean
    private NoteService noteService;

    @Test
    void getAllNotes() throws Exception {

        mockMvc.perform(get("/note/all"))
                .andExpect(status().isOk());
    }

    @Test
    void findNoteByPatientId() throws Exception {

        mockMvc.perform(get("/note/patient/{patientId}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void saveNote() throws Exception {

        mockMvc.perform(post("/note/add"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNote() throws Exception {

        mockMvc.perform(delete("/note/delete/{id}", "1"))
                .andExpect(status().isOk());
    }
}