package com.health.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.health.demo.entity.Doctor;
import com.health.demo.entity.Patient;
import com.health.demo.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
         if (doctorRepository.findByEmail(doctor.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered!");
        }
        return (Doctor) doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with ID " + id + " not found"));
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setContactNumber(updatedDoctor.getContactNumber());
        existingDoctor.setAvailability(updatedDoctor.getAvailability());
        return doctorRepository.save(existingDoctor);
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with ID " + id + " not found"));
    }

    public Optional<Doctor> findByEmail(String email) {
    	return doctorRepository.findByEmail(email);

	
    }
}


