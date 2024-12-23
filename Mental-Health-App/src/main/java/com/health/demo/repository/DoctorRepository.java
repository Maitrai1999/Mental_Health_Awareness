package com.health.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.demo.entity.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository <Doctor , Long > {
	Optional<Doctor> findByEmail(String email);
}
