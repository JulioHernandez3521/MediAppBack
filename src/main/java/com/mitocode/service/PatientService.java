package com.mitocode.service;

import com.mitocode.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService extends ICRUD<Patient,Integer>{
    Page<Patient> listPage(Pageable pageable);

}
