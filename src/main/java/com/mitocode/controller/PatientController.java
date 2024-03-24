package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.impl.PatientServiceImpl;
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

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        Patient obj = this.service.save(patient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable("id")Integer id, @RequestBody Patient patient) {
        patient.setId(id);
        return ResponseEntity.ok(this.service.update(id,patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
