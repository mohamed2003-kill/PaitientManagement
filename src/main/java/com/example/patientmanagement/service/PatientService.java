package com.example.patientmanagement.service;

import com.example.patientmanagement.entity.Patient;
import com.example.patientmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repo;

    public Page<Patient> listAll(int pageNum, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("lastName"));
        if (keyword != null && !keyword.isBlank()) {
            var list = repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword);
            int start = Math.min((int) pageable.getOffset(), list.size());
            int end = Math.min(start + pageable.getPageSize(), list.size());
            return new PageImpl<>(list.subList(start, end), pageable, list.size());
        }
        return repo.findAll(pageable);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Patient get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Patient save(Patient patient) {
        return repo.save(patient);
    }
}
