package com.medilabo.note.service.implementation;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.exception.NoteNotFoundException;
import com.medilabo.note.mapper.NoteMapper;
import com.medilabo.note.mapper.SaveNoteMapper;
import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import com.medilabo.note.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final SaveNoteMapper saveNoteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper, SaveNoteMapper saveNoteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.saveNoteMapper = saveNoteMapper;
    }

    @Override
    public List<NoteDTO> findAll() {

        return noteRepository.findAll()
                .stream()
                .map(noteMapper::noteToNoteDTO)
                .toList();
    }

    @Override
    public List<NoteDTO> findByPatientId(Long patientId) {

        return noteRepository.findByPatientId(patientId)
                .stream()
                .map(noteMapper::noteToNoteDTO)
                .toList();
    }

    @Override
    public SaveNoteDTO createNote(SaveNoteDTO saveNoteDTO) {

        Note note = saveNoteMapper.saveNoteDTOToNote(saveNoteDTO);
        Note saved = noteRepository.save(note);

        return saveNoteMapper.saveNoteToSaveNoteDTO(saved);
    }

    @Override
    public void deleteNote(String id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(()-> new NoteNotFoundException("Note not found with id: " + id));

        noteRepository.delete(note);
    }
}
