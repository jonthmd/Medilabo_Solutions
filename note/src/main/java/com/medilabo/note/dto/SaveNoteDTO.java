package com.medilabo.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used to represent a saved note.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveNoteDTO {

    private Long patientId;
    private String note;
}
