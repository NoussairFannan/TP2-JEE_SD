package org.example.patientapp.WebService;

import org.example.patientapp.Entities.Patient;
import org.example.patientapp.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientRestService {
    @Autowired
    private PatientRepository patientRepository;
    @PostMapping("/save")
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(Patient patient) {
        if (!patientRepository.existsById(patient.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientRepository.save(patient));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Patient> deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        patientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientRepository.findById(id).get();
    }

    @GetMapping("/all")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

}
