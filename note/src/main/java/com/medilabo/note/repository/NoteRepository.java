package com.medilabo.note.repository;

import com.medilabo.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing {@link Note}.
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByPatientId(Long patientId);
    void deleteByPatientId(Long patientId);
}
