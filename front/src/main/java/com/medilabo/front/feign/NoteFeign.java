package com.medilabo.front.feign;

import com.medilabo.front.dto.NoteDTO;
import com.medilabo.front.dto.SaveNoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note", url = "http://localhost:8080")
public interface NoteFeign {

    @GetMapping("/api/note/patient/{patientId}")
    List<NoteDTO> findNoteByPatientId(@PathVariable Long patientId);

    @PostMapping("/api/note/add")
    SaveNoteDTO saveNote(SaveNoteDTO saveNoteDTO);

    @DeleteMapping("/api/note/delete/{id}")
    void deleteNote(@PathVariable String id);

    @DeleteMapping("/api/note/delete/notes/patient/{patientId}")
    void deleteNoteByPatientId(@PathVariable Long patientId);
}
