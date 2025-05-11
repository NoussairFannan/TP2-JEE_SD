package org.example.patientapp.Repositories;

import org.example.patientapp.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByName(String nom);
}
