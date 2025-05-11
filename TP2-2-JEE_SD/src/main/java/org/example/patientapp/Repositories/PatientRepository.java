package org.example.patientapp.Repositories;

import org.example.patientapp.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String nom);
}
