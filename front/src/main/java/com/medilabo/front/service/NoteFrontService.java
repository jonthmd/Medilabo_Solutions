package com.medilabo.front.service;

import com.medilabo.front.dto.NoteDTO;
import com.medilabo.front.dto.SaveNoteDTO;

import java.util.List;

/**
 * Service interface managing operations related to front for note.
 */
public interface NoteFrontService {

    List<NoteDTO> findNoteByPatientId(Long patientId);
    SaveNoteDTO saveNote(SaveNoteDTO saveNoteDTO);
    void deleteNote(String id);
    void deleteNoteByPatientId(Long patientId);
}
