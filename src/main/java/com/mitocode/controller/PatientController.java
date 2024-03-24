package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.dto.PatientDto;
import com.mitocode.service.impl.PatientServiceImpl;
import com.mitocode.service.mappers.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${patient.controller.path}")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServiceImpl service;
    private final PatientMapper patientMapper;

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(patientMapper::toDto).toList());
    }
//    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.patientMapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PatientDto> save(@RequestBody PatientDto patient){
        PatientDto obj =this.patientMapper.toDto(this.service.save(this.patientMapper.toEntity(patient)));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable("id")Integer id, @RequestBody PatientDto patient) {
        patient.setId(id);
        return ResponseEntity.ok(this.patientMapper.toDto(this.service.update(id,this.patientMapper.toEntity(patient))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
