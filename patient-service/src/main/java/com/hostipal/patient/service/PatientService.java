package com.hostipal.patient.service;

import org.springframework.stereotype.Service;

import com.hostipal.patient.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
}
