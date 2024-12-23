package com.health.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.demo.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository <Patient , Long > {

	

	Optional<Patient> findByEmail(String email);

	Optional<Patient> findByPid(Long pid);
	

}
