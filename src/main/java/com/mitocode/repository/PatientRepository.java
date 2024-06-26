package com.mitocode.repository;

import com.mitocode.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends IGenericRepo<Patient, Integer> {
}
