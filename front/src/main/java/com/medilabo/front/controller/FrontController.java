package com.medilabo.front.controller;

import com.medilabo.front.dto.SaveNoteDTO;
import com.medilabo.front.dto.SavePatientDTO;
import com.medilabo.front.service.NoteFrontService;
import com.medilabo.front.service.PatientFrontService;
import com.medilabo.front.service.RiskFrontService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller with endpoints operations related to the front.
 */
@Slf4j
@Controller
public class FrontController {

    private final PatientFrontService patientFrontService;
    private final NoteFrontService noteFrontService;
    private final RiskFrontService riskFrontService;

    public FrontController(PatientFrontService patientFrontService, NoteFrontService noteFrontService, RiskFrontService riskFrontService) {
        this.patientFrontService = patientFrontService;
        this.noteFrontService = noteFrontService;
        this.riskFrontService = riskFrontService;
    }

    /**
     * Displays the patients list.
     *
     * @param model Model used to transmit data.
     * @return The dashboard with the patients list.
     */
    @GetMapping("/list")
    public String patientList(Model model) {
        model.addAttribute("patients", patientFrontService.getAllPatients());
        return "list";
    }

    /**
     * Validates the search request.
     *
     * @param lastName The specified patient to search by the last name.
     * @param model    Model used to transmit data.
     * @return The searched patient on the dashboard.
     */
    @GetMapping("/search")
    public String searchPatient(@RequestParam String lastName, Model model) {
        model.addAttribute("patients", patientFrontService.searchPatients(lastName));
        return "list";
    }

    /**
     * Displays the patient details.
     *
     * @param id    The id of the selected patient.
     * @param model Model used to transmit data.
     * @return The patient details with associated notes and risk evaluation.
     */
    @GetMapping("/patient/{id}")
    public String getPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientFrontService.getPatientById(id));
        model.addAttribute("notes", noteFrontService.findNoteByPatientId(id));
        model.addAttribute("saveNoteDTO", new SaveNoteDTO());
        model.addAttribute("risk", riskFrontService.getRisk(id));
        log.info("get patient {}", id);
        return "details";
    }

    /**
     * Displays the patient registration form.
     *
     * @param model Model used to transmit data.
     * @return The registration form.
     */
    @GetMapping("/patient/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("savePatientDTO", new SavePatientDTO());
        return "add";
    }

    /**
     * Validates the registered patient.
     *
     * @param savePatientDTO DTO used to represent a saved patient.
     * @param result         The data validation, displays error message if any.
     * @return A redirection to the dashboard.
     */
    @PostMapping("/patient/add")
    public String addPatient(@Valid @ModelAttribute SavePatientDTO savePatientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        patientFrontService.addPatient(savePatientDTO);
        log.info("add patient {}", savePatientDTO);
        return "redirect:/list";
    }

    /**
     * Displays the edit form.
     *
     * @param id    The id of the selected patient.
     * @param model Model used to transmit data.
     * @return The edit form.
     */
    @GetMapping("/patient/update/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientFrontService.getPatientById(id));
        model.addAttribute("id", id);
        return "update";
    }

    /**
     * Validates the edition of the patient.
     *
     * @param savePatientDTO DTO used to represent a saved patient.
     * @param result         The data validation, displays error message if any.
     * @param id             The id of the selected patient.
     * @param model          Model used to transmit data.
     * @return The patient details with associated notes and risk evaluation if successful edition.
     */
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@Valid @ModelAttribute("patient") SavePatientDTO savePatientDTO, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            SavePatientDTO existingPatient = patientFrontService.updatePatient(id, savePatientDTO);

            if (savePatientDTO.getBirthDate() == null) {
                savePatientDTO.setBirthDate(existingPatient.getBirthDate());
            }
            model.addAttribute("id", id);
            return "update";
        }
        patientFrontService.updatePatient(id, savePatientDTO);
        log.info("update patient {}", savePatientDTO);
        return "redirect:/patient/" + id;
    }

    /**
     * Deletes an existing patient.
     *
     * @param id The id of the selected patient.
     * @return The dashboard with the patients list.
     */
    @PostMapping("patient/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        noteFrontService.deleteNoteByPatientId(id);
        patientFrontService.deletePatient(id);
        log.info("delete patient {}", id);
        return "redirect:/list";
    }

    /**
     * Creates a note on the selected patient details.
     *
     * @param saveNoteDTO DTO used to represent a saved note.
     * @return The patient details with associated notes and risk evaluation.
     */
    @PostMapping("/note/add")
    public String addNote(@ModelAttribute SaveNoteDTO saveNoteDTO) {
        noteFrontService.saveNote(saveNoteDTO);
        log.info("add note {}", saveNoteDTO);
        return "redirect:/patient/" + saveNoteDTO.getPatientId();
    }

    /**
     * Deletes an existing note.
     *
     * @param id        The id of the selected note.
     * @param patientId The id of the selected patient.
     * @return The patient details with associated notes and risk evaluation.
     */
    @PostMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id, @RequestParam String patientId) {
        noteFrontService.deleteNote(id);
        log.info("delete note {}", id);
        return "redirect:/patient/" + patientId;
    }
}
