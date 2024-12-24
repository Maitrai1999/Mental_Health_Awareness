package com.health.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.demo.entity.Patient;
import com.health.demo.repository.PatientRepository;


@Service
public class PatientService {
	
	@Autowired
    private final PatientRepository patientRepository;

    
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Register a new patient
    public Patient register(Patient patient) {
    	 Optional<Patient> existingPatient = patientRepository.findByEmail(patient.getEmail());
         if (existingPatient.isPresent()) {
             // Throw an exception with a specific message
             throw new IllegalArgumentException("Email already exists");
         }

         // If no duplicate found, save the new patient
         return patientRepository.save(patient);
     
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get a patient by ID
    public Patient getPatientByPid(Long pid) {
        Optional<Patient> patient = patientRepository.findByPid(pid);
        return patient.orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        // Find the existing patient by ID
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found"));

        existingPatient.setName(patientDetails.getName());
        existingPatient.setEmail(patientDetails.getEmail());
        existingPatient.setNumber(patientDetails.getNumber());
        existingPatient.setAddress(patientDetails.getAddress());
        existingPatient.setPassword(patientDetails.getPassword());       
        return patientRepository.save(existingPatient);
    }

    // User login validation
    public boolean loginUser(String email, String password) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        return patient.isPresent() && patient.get().getPassword().equals(password);
    }

	
}

