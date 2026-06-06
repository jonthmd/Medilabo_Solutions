package com.medilabo.note.controller;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller with endpoints operations related to note.
 */
@Slf4j
@RestController
@RequestMapping("/note")
@Tag(name = "Note", description = "Patients Notes of Medilabo Solutions.")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Retrieves all notes.
     *
     * @return A full notes list.
     */
    @GetMapping("/all")
    @Operation(summary = "Get all notes.")
    public List<NoteDTO> getAllNotes() {
        log.info("Start getAllNotes...");
        return noteService.findAll();
    }

    /**
     * Retrieves notes linked to a selected patient.
     *
     * @param patientId The id of the selected patient.
     * @return A list of notes linked to a selected patient.
     */
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all notes for a patient.")
    public List<NoteDTO> findNoteByPatientId(@PathVariable Long patientId) {
        log.info("Start findByPatientId...");
        return noteService.findByPatientId(patientId);
    }

    /**
     * Creates a note.
     *
     * @param saveNoteDTO DTO used to represent a saved note.
     * @return A note saved and linked to a patient.
     */
    @PostMapping("/add")
    @Operation(summary = "Add a note.")
    public SaveNoteDTO saveNote(@RequestBody SaveNoteDTO saveNoteDTO) {
        log.info("Start save note...");
        return noteService.createNote(saveNoteDTO);
    }

    /**
     * Deletes an existing note.
     *
     * @param id The id of the selected note.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a note.")
    public void deleteNote(@PathVariable String id) {
        log.info("Start delete note...");
        noteService.deleteNote(id);
    }

    /**
     * Deletes an existing note via a selected patient.
     *
     * @param patientId The id of the selected patient.
     */
    @DeleteMapping("/delete/notes/patient/{patientId}")
    @Operation(summary = "Delete notes by patientId. ")
    public void deleteNoteByPatientId(@PathVariable Long patientId) {
        log.info("Start deleteNoteByPatientId...");
        noteService.deleteNoteByPatientId(patientId);
    }
}
