package com.medilabo.note.mapper;

import com.medilabo.note.dto.SaveNoteDTO;
import com.medilabo.note.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveNoteMapper {

    SaveNoteDTO saveNoteToSaveNoteDTO(Note note);
    Note saveNoteDTOToNote(SaveNoteDTO saveNoteDTO);
}
