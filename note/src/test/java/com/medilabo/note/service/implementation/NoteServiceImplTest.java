package com.medilabo.note.service.implementation;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.mapper.NoteMapper;
import com.medilabo.note.mapper.SaveNoteMapper;
import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper noteMapper;

    @Mock
    private SaveNoteMapper saveNoteMapper;

    @InjectMocks
    private NoteServiceImpl classUnderTest;

    @Test
    void findAll() {

        //GIVEN
        Note note = new Note();
        List<Note> noteList = List.of(note);

        NoteDTO noteDTO = new NoteDTO();
        List<NoteDTO> noteDTOList = List.of(noteDTO);

        when(noteRepository.findAll()).thenReturn(noteList);
        when(noteMapper.noteToNoteDTO(note)).thenReturn(noteDTO);

        //WHEN
        List<NoteDTO> result = classUnderTest.findAll();

        //THEN
        verify(noteRepository).findAll();
        verify(noteMapper).noteToNoteDTO(note);
        assertThat(result).isEqualTo(noteDTOList);
    }

    @Test
    void findByPatientId() {

        //GIVEN
        Note note = new Note();
        List<Note> noteList = List.of(note);

        NoteDTO noteDTO = new NoteDTO();
        List<NoteDTO> noteDTOList = List.of(noteDTO);

        when(noteRepository.findByPatientId("1")).thenReturn(noteList);
        when(noteMapper.noteToNoteDTO(note)).thenReturn(noteDTO);

        //WHEN
        List<NoteDTO> result = classUnderTest.findByPatientId("1");

        //THEN
        verify(noteRepository).findByPatientId("1");
        verify(noteMapper).noteToNoteDTO(note);
        assertThat(result).isEqualTo(noteDTOList);
    }

    @Test
    void createNote() {

        //GIVEN
        Note note = new Note();
        SaveNoteDTO saveNoteDTO = new SaveNoteDTO();

        when(saveNoteMapper.saveNoteDTOToNote(saveNoteDTO)).thenReturn(note);
        when(noteRepository.save(note)).thenReturn(note);
        when(saveNoteMapper.saveNoteToSaveNoteDTO(note)).thenReturn(saveNoteDTO);

        //WHEN
        SaveNoteDTO result = classUnderTest.createNote(saveNoteDTO);

        //THEN
        verify(saveNoteMapper).saveNoteDTOToNote(saveNoteDTO);
        verify(noteRepository).save(note);
        verify(saveNoteMapper).saveNoteToSaveNoteDTO(note);
        assertThat(result).isEqualTo(saveNoteDTO);
    }

    @Test
    void deleteNote() {

        //GIVEN
        Note note = new Note();

        when(noteRepository.findById("1")).thenReturn(Optional.of(note));

        //WHEN
        classUnderTest.deleteNote("1");

        //THEN
        verify(noteRepository).findById("1");
    }

    @Test
    void deleteNoteException() {

        //GIVEN
        when(noteRepository.findById("1")).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThrows(RuntimeException.class, () -> classUnderTest.deleteNote("1"));
    }
}