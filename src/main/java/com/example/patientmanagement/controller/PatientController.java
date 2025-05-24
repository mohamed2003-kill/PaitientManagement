package com.example.patientmanagement.controller;

import com.example.patientmanagement.entity.Patient;
import com.example.patientmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {
    @Autowired
    private PatientService service;

    @GetMapping("/patients")
    public String viewPatients(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(required = false) String keyword,
            Model model) {

        Page<Patient> pageData = service.listAll(page, size, keyword);
        model.addAttribute("patients", pageData.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageData.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/patients";
    }


    @GetMapping("/patients/new")
    public String showNewForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient_form";
    }

    @PostMapping("/patients/save")
    public String savePatient(
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            // if using fragments, make sure the model has pageTitle
            model.addAttribute("pageTitle", patient.getId()==null? "New Patient" : "Edit Patient");
            return "patient_form";
        }
        service.save(patient);
        return "redirect:/patients";
    }

}
