package com.hostipal.patient.mapper;

import java.time.LocalDate;

import com.hostipal.patient.dto.PatientRequestDTO;
import com.hostipal.patient.dto.PatientResponseDTO;
import com.hostipal.patient.model.Patient;

public class PatientMapper {

    public static PatientResponseDTO toDTo(Patient patient){
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId().toString());
        dto.setName(patient.getName());
        dto.setAddress(patient.getAddress());
        dto.setEmail(patient.getEmail());
        dto.setDateOfBirth(patient.getDateOfBirth().toString());
        return dto;
    }

  public static Patient toModel(PatientRequestDTO patientRequestDTO) {
    Patient patient = new Patient();
    patient.setName(patientRequestDTO.getName());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setEmail(patientRequestDTO.getEmail());
    patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
    patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
    return patient;
  }
    
}
