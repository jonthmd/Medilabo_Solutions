package com.medilabo.note.controller;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.service.NoteService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteService noteService;

    @Test
    void getAllNotes() throws Exception {

        //GIVEN
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(1L);
        noteDTO.setNote("note");

        when(noteService.findAll()).thenReturn(List.of(noteDTO));

        //WHEN+THEN
        mockMvc.perform(get("/note/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].note").value("note"));

        verify(noteService).findAll();
    }

    @Test
    void findNoteByPatientId() throws Exception {

        //GIVEN
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(1L);
        noteDTO.setNote("note");

        when(noteService.findByPatientId(1L)).thenReturn(List.of(noteDTO));

        //WHEN+THEN
        mockMvc.perform(get("/note/patient/{patientId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientId").value(1L));

        verify(noteService).findByPatientId(1L);
    }

    @Test
    void saveNote() throws Exception {

        //GIVEN
        String json = """
                                {
                  "note": "note"
                }
                """;

        SaveNoteDTO saveNoteDTO = new SaveNoteDTO();
        saveNoteDTO.setNote("note");

        when(noteService.createNote(any(SaveNoteDTO.class))).thenReturn(saveNoteDTO);

        //WHEN+THEN
        mockMvc.perform(post("/note/add")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value("note"));

        verify(noteService).createNote(any(SaveNoteDTO.class));
    }

    @Test
    void deleteNote() throws Exception {

        mockMvc.perform(delete("/note/delete/{id}", "1"))
                .andExpect(status().isOk());

        verify(noteService).deleteNote("1");
    }

    @Test
    void deleteNoteByPatientId() throws Exception {

        mockMvc.perform(delete("/note/delete/notes/patient/{patientId}", 1L))
                .andExpect(status().isOk());

        verify(noteService).deleteNoteByPatientId(1L);
    }
}