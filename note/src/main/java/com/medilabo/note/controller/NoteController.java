package com.medilabo.note.controller;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Note", description = "Patients Notes of Medilabo Solutions.")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note/all")
    @Operation(summary = "Get all notes.")
    public List<NoteDTO> getAllNotes() {
        log.info("Start getAllNotes...");
        return noteService.findAll();
    }

    @GetMapping("/note/patient/{patientId}")
    @Operation(summary = "Get all notes for a patient.")
    public List<NoteDTO> findNoteByPatientId(@PathVariable String patientId) {
        log.info("Start findByPatientId...");
        return noteService.findByPatientId(patientId);
    }

    @PostMapping("/note/add")
    @Operation(summary = "Add a note.")
    public SaveNoteDTO saveNote(SaveNoteDTO saveNoteDTO) {
        log.info("Start save note...");
        return noteService.createNote(saveNoteDTO);
    }

    @DeleteMapping("/note/delete/{id}")
    @Operation(summary = "Delete a note.")
    public void deleteNote(@PathVariable String id) {
        log.info("Start delete note...");
        noteService.deleteNote(id);
    }
}
