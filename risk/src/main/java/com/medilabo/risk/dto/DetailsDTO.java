package com.medilabo.risk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDTO {

    private PatientDTO patient;
    private List<NoteDTO> note;
}
