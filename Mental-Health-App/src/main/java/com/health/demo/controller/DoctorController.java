package com.health.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.demo.entity.Doctor;
import com.health.demo.service.DoctorService;



@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        try {
            // Check if a doctor with the same email already exists
            Optional<Doctor> existingDoctor = doctorService.findByEmail(doctor.getEmail());
            
            if (existingDoctor.isPresent()) {
                // If doctor already exists, return a conflict response (HTTP 409)
                Map<String, String> response = new HashMap<>();
                response.put("message", "Doctor with this email already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);  // Optional: Include the response body here if needed
            }
            
            // If no duplicate found, save the new doctor
            Doctor savedDoctor = doctorService.addDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor); // HTTP 201 for creation
        } catch (Exception e) {
                 e.printStackTrace();  // Or use a logger for better logging
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    // Update an existing doctor by ID
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        try {
            Doctor doctor = doctorService.updateDoctor(id, updatedDoctor);
            if (doctor != null) {
                return ResponseEntity.ok(doctor); // Return the updated doctor with HTTP 200 status
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if doctor is not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return 400 in case of error
        }
    }

    // Delete a doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        try {
            boolean isDeleted = doctorService.deleteDoctor(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }



    // Get a list of all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return ResponseEntity.ok(doctors); // Return the list of doctors with HTTP 200 status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 in case of error
        }
    }

    // Get a doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor != null) {
                return ResponseEntity.ok(doctor); // Return the doctor with HTTP 200 status
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if doctor is not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 in case of error
        }
    }
}

