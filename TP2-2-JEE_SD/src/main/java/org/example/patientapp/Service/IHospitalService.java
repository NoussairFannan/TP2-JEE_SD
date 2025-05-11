package org.example.patientapp.Service;

import org.example.patientapp.Entities.Consultation;
import org.example.patientapp.Entities.Medecin;
import org.example.patientapp.Entities.Patient;
import org.example.patientapp.Entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
