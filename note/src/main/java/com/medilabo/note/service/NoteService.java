package com.medilabo.note.service;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;

import java.util.List;

public interface NoteService {

    List<NoteDTO> findAll();
    List<NoteDTO> findByPatientId(String patientId);
    SaveNoteDTO createNote(SaveNoteDTO saveNoteDTO);
    void deleteNote(String id);
}
