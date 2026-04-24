package com.medilabo.note.repository;

import com.medilabo.note.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void findByPatientId() {

        //GIVEN
        Note note = new Note();
        note.setPatientId("1");
        noteRepository.save(note);

        //WHEN
        List<Note> result = noteRepository.findByPatientId("1");

        //THEN
        assertThat(result).hasSize(1)
        .extracting(Note::getPatientId)
                .containsExactly("1");
    }
}