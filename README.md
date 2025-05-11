# Application de Gestion des Patients

## 1. Introduction
Ce projet est une application de gestion hospitalière développée en Java Enterprise Edition (JEE) utilisant Spring Boot. L'application permet de gérer les patients, les médecins, les rendez-vous et les consultations dans un établissement médical.

## 2. Architecture Technique

### 2.1 Technologies Utilisées
- **Framework Principal** : Spring Boot
- **Persistence** : JPA/Hibernate
- **Gestion des Dépendances** : Maven
- **API** : RESTful Web Services
- **Outils de Développement** : Lombok pour la réduction du code boilerplate

### 2.2 Structure du Projet
Le projet suit une architecture en couches avec une séparation claire des responsabilités :

```
src/main/java/org/example/patientapp/
├── Entities/
├── Repositories/
├── Service/
├── WebService/
└── PatientAppApplication.java
```

## 3. Modèle de Données

### 3.1 Entités Principales

#### Patient
```java
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVous;
}
```

#### Autres Entités
- **Medecin** : Représente les médecins de l'établissement
- **RendezVous** : Gère les rendez-vous entre patients et médecins
- **Consultation** : Représente les consultations médicales
- **StatusRDV** : Énumération pour le statut des rendez-vous

## 4. Couche de Persistance

### 4.1 Repositories
L'application utilise Spring Data JPA avec les repositories suivants :
- `PatientRepository`
- `MedecinRepository`
- `RendezVousRepository`
- `ConsultationRepository`

Chaque repository étend `JpaRepository` pour bénéficier des opérations CRUD de base.

## 5. Couche Service

### 5.1 Interface de Service
```java
public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
```

### 5.2 Implémentation
L'implémentation `IHospitalServiceImpl` fournit la logique métier pour :
- La gestion des patients
- La gestion des médecins
- La gestion des rendez-vous
- La gestion des consultations

## 6. Couche Web (REST API)

### 6.1 Contrôleur Patient
```java
@RestController
@RequestMapping("/patients")
public class PatientRestService {
    // Endpoints REST
    @PostMapping("/save")
    @PutMapping("/update")
    @DeleteMapping("/delete")
    @GetMapping("/{id}")
    @GetMapping("/all")
}
```

### 6.2 Endpoints Disponibles
- **POST /patients/save** : Création d'un nouveau patient
- **PUT /patients/update** : Mise à jour d'un patient existant
- **DELETE /patients/delete** : Suppression d'un patient
- **GET /patients/{id}** : Récupération d'un patient par ID
- **GET /patients/all** : Liste de tous les patients

## 7. Fonctionnalités Principales

### 7.1 Gestion des Patients
- Création de nouveaux patients
- Mise à jour des informations patient
- Suppression de patients
- Consultation des dossiers patients
- Liste complète des patients

### 7.2 Gestion des Rendez-vous
- Prise de rendez-vous
- Suivi du statut des rendez-vous
- Association patient-médecin

### 7.3 Gestion des Consultations
- Enregistrement des consultations
- Suivi des consultations par patient
- Historique médical

## 8. Points Techniques Notables

### 8.1 Gestion des Relations
- Relation One-to-Many entre Patient et RendezVous
- Utilisation de FetchType.LAZY pour l'optimisation des performances

### 8.2 Sécurité des Données
- Validation des opérations de mise à jour et suppression
- Gestion des erreurs avec ResponseEntity

### 8.3 Optimisations
- Utilisation de Lombok pour réduire le code boilerplate
- Configuration JPA optimisée
- Gestion efficace des relations entre entités

## 9. Conclusion
Cette application démontre une implémentation robuste d'un système de gestion hospitalière en utilisant les meilleures pratiques de développement JEE. L'architecture en couches, la séparation des responsabilités et l'utilisation appropriée des technologies modernes en font une base solide pour une application d'entreprise. 