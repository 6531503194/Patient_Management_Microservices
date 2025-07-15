package com.hostipal.patient.repository;

import org.springframework.stereotype.Repository;

import com.hostipal.patient.model.Patient;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
