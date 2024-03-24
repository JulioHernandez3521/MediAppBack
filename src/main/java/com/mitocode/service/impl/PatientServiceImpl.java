package com.mitocode.service.impl;

import com.mitocode.model.Patient;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.PatientRepository;
import com.mitocode.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient,Integer> implements PatientService {
    private final PatientRepository repository;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return this.repository;
    }
}
