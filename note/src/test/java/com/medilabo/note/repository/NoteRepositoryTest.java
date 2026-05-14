package com.medilabo.note.repository;

import com.medilabo.note.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
class NoteRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0")
            .waitingFor(Wait.forListeningPort());

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void findByPatientId() {

        //GIVEN
        Note note = new Note();
        note.setPatientId(1L);
        noteRepository.save(note);

        //WHEN
        List<Note> result = noteRepository.findByPatientId(1L);

        //THEN
        assertThat(result).hasSize(1)
        .extracting(Note::getPatientId)
                .containsExactly(1L);
    }
}