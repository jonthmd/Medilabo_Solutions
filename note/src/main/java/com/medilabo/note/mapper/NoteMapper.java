package com.medilabo.note.mapper;

import com.medilabo.note.dto.NoteDTO;
import com.medilabo.note.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDTO noteToNoteDTO(Note note);
    Note noteDTOToNote(NoteDTO noteDTO);
}
