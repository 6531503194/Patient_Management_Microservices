package com.hostipal.patient.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hostipal.patient.dto.PatientRequestDTO;
import com.hostipal.patient.dto.PatientResponseDTO;
import com.hostipal.patient.exception.EmailAlreadyExistsException;
import com.hostipal.patient.exception.PatientNotFoundException;
import com.hostipal.patient.mapper.PatientMapper;
import com.hostipal.patient.model.Patient;
import com.hostipal.patient.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {

        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs =  patients.stream()
                .map(patient -> PatientMapper.toDTo(patient))
                    .toList();
        
        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with email " + patientRequestDTO.getEmail() + " already exists");
        }

        Patient patient = PatientMapper.toModel(patientRequestDTO);
        patient = patientRepository.save(patient);
        return PatientMapper.toDTo(patient);
    }

    
    public PatientResponseDTO updatePatient(UUID id,
        PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(id).orElseThrow(
            () -> new PatientNotFoundException("Patient not found with ID: " + id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
        throw new EmailAlreadyExistsException(
            "A patient with this email " + "already exists"
                + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTo(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
    
}
