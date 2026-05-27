package com.medilabo.risk.utils;

import com.medilabo.risk.dto.NoteDTO;
import com.medilabo.risk.model.Triggers;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.List;

@Component
public class CountTriggers {

    public int count(List<NoteDTO> notes) {
        int count = 0;
        for (NoteDTO noteDTO : notes) {

            String note = Normalizer.normalize(
                            noteDTO.getNote(),
                            Normalizer.Form.NFD
                    ).replaceAll("\\p{M}", "")
                    .toLowerCase();
            for (Triggers triggers : Triggers.values()) {
                if (note.contains(triggers.name().toLowerCase())) {
                    count++;
                }
            }
        }
        return count;
    }
}
