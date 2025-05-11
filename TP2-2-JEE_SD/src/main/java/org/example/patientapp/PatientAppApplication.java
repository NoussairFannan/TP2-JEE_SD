package org.example.patientapp;
import org.example.patientapp.Entities.*;
import org.example.patientapp.Repositories.ConsultationRepository;
import org.example.patientapp.Repositories.MedecinRepository;
import org.example.patientapp.Repositories.PatientRepository;
import org.example.patientapp.Repositories.RendezVousRepository;
import org.example.patientapp.Service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication

public class PatientAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientAppApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IHospitalService iHospitalService, PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository){
        return args ->{
            Stream.of("Hassan", "Karim", "Omar", "Salah").forEach(name -> {
                Patient patient = new Patient();
                patient.setName(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                iHospitalService.savePatient(patient);
            });
            Stream.of("Ahmed", "Ishak", "Nabil", "Marouane").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setName(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardiologue":"Dentiste");
                iHospitalService.saveMedecin(medecin);
            });
            Patient patient = patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByName("Hassan");
            Medecin medecin = medecinRepository.findByName("Nabil");
            System.out.println("**********************************");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDRV(new Date());
            rendezVous.setStatusRDV(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            iHospitalService.saveRendezVous(rendezVous);
            System.out.println("**********************************");
            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("");
            iHospitalService.saveConsultation(consultation);
        };
    }

}
