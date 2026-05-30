package com.medilabo.front.controller;

import com.medilabo.front.dto.DetailsDTO;
import com.medilabo.front.dto.SaveNoteDTO;
import com.medilabo.front.dto.SavePatientDTO;
import com.medilabo.front.feign.NoteFeign;
import com.medilabo.front.feign.PatientFeign;
import com.medilabo.front.feign.RiskFeign;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class FrontController {

    private final PatientFeign patientFeign;
    private final NoteFeign noteFeign;
    private final RiskFeign riskFeign;

    public FrontController(PatientFeign patientFeign, NoteFeign noteFeign, RiskFeign riskFeign) {
        this.patientFeign = patientFeign;
        this.noteFeign = noteFeign;
        this.riskFeign = riskFeign;
    }

    @GetMapping("/list")
    public String patientList(Model model) {
        model.addAttribute("patients", patientFeign.getAllPatients());
        log.info("patient list");
        return "list";
    }

    @GetMapping("/search")
    public String searchPatient(@RequestParam String lastName, Model model) {
        model.addAttribute("patients", patientFeign.searchPatients(lastName));
        return "list";
    }

    @GetMapping("/patient/{id}")
    public String getPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientFeign.getPatientById(id));
        model.addAttribute("notes", noteFeign.findNoteByPatientId(id));
        model.addAttribute("saveNoteDTO", new SaveNoteDTO());
        DetailsDTO detailsDTO = new DetailsDTO(patientFeign.getPatientById(id), noteFeign.findNoteByPatientId(id));
        model.addAttribute("risk", riskFeign.getRisk(detailsDTO));
        log.info("get patient {}", id);
        log.info("risk : {}", riskFeign.getRisk(detailsDTO));
        return "details";
    }

    @GetMapping("/patient/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("savePatientDTO", new SavePatientDTO());
        return "add";
    }

    @PostMapping("/patient/add")
    public String addPatient(@Valid @ModelAttribute SavePatientDTO savePatientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        patientFeign.addPatient(savePatientDTO);
        log.info("add patient {}", savePatientDTO);
        return "redirect:/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientFeign.getPatientById(id));
        model.addAttribute("id", id);
        return "update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@Valid @ModelAttribute("patient") SavePatientDTO savePatientDTO, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors()
                    .forEach(error -> log.error(error.toString()));
            SavePatientDTO existingPatient = patientFeign.updatePatient(id, savePatientDTO);

            if (savePatientDTO.getBirthDate() == null) {
                savePatientDTO.setBirthDate(existingPatient.getBirthDate());
            }
            model.addAttribute("id", id);
            return "update";
        }
        patientFeign.updatePatient(id, savePatientDTO);
        log.info("update patient {}", savePatientDTO);
        return "redirect:/patient/" + id;
    }

    @PostMapping("patient/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        noteFeign.deleteNoteByPatientId(id);
        patientFeign.deletePatient(id);
        log.info("delete patient {}", id);
        return "redirect:/list";
    }

    @PostMapping("/note/add")
    public String addNote(@ModelAttribute SaveNoteDTO saveNoteDTO) {
        noteFeign.saveNote(saveNoteDTO);
        log.info("add note {}", saveNoteDTO);
        return "redirect:/patient/" + saveNoteDTO.getPatientId();
    }

    @PostMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id, @RequestParam String patientId) {
        noteFeign.deleteNote(id);
        log.info("delete note {}", id);
        return "redirect:/patient/" + patientId;
    }
}
