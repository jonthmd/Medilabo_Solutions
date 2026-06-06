package com.medilabo.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used to represent a note.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private String id;
    private Long patientId;
    private String note;
}
