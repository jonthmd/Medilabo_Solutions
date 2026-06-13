package com.medilabo.front.service.implementation;

import com.medilabo.front.dto.NoteDTO;
import com.medilabo.front.dto.SaveNoteDTO;
import com.medilabo.front.feign.NoteFeign;
import com.medilabo.front.service.NoteFrontService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the note front service interface.
 */
@Service
public class NoteFrontServiceImpl implements NoteFrontService {

    private final NoteFeign noteFeign;

    public NoteFrontServiceImpl(NoteFeign noteFeign) {
        this.noteFeign = noteFeign;
    }

    /**
     * Retrieves a list of notes via a patient id.
     *
     * @param patientId The id of the selected patient.
     * @return A list of noteDTO.
     */
    @Override
    public List<NoteDTO> findNoteByPatientId(Long patientId) {
        return noteFeign.findNoteByPatientId(patientId);
    }

    /**
     * Creates a note.
     *
     * @param saveNoteDTO Mapped object containing the note to be created.
     * @return SaveNoteDTO, the created note.
     */
    @Override
    public SaveNoteDTO saveNote(SaveNoteDTO saveNoteDTO) {
        return noteFeign.saveNote(saveNoteDTO);
    }

    /**
     * Deletes an existing note.
     *
     * @param id The specified note id.
     */
    @Override
    public void deleteNote(String id) {
        noteFeign.deleteNote(id);
    }

    /**
     * Deletes an existing note via a patient id.
     *
     * @param patientId The specified patient id.
     */
    @Override
    public void deleteNoteByPatientId(Long patientId) {
        noteFeign.deleteNoteByPatientId(patientId);
    }
}
