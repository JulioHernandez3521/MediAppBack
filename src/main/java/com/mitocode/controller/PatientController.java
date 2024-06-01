package com.mitocode.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.model.Patient;
import com.mitocode.service.dto.PatientDto;
import com.mitocode.service.impl.PatientServiceImpl;
import com.mitocode.service.mappers.PatientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("${patient.controller.path}")
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(PatientController.class);
    private final PatientServiceImpl service;
    private final PatientMapper patientMapper;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(patientMapper::toDto).toList());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<PatientDto>> findAllPageable(Pageable pageable){
        return  ResponseEntity.ok(this.patientMapper.toDtoPage(this.service.listPage(pageable)));
    }

//    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.patientMapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PatientDto> save(@Valid @RequestBody PatientDto patient){
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
    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDto> findByHateoas(@PathVariable("id") Integer id){
        EntityModel<PatientDto> resource = EntityModel.of(this.patientMapper.toDto(this.service.findById(id)));
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info-byId"));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resource.add(link2.withRel("patient-info-all"));
        return resource;
    }

    @PostMapping("/seed")
    public ResponseEntity<List<PatientDto>> saveAll(){
        List<PatientDto> data = new ArrayList<>();
        TypeReference<List<PatientDto>> typeReference = new TypeReference<List<PatientDto>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/patients.json");
        try {
            data = this.objectMapper.readValue(inputStream,typeReference);
            data = this.patientMapper.toDtoList(this.service.saveAll(this.patientMapper.toEntityList(data)));
            log.info("Total items saved: {}",data.size());
        } catch (IOException e){
           log.error(e.getMessage());
        }
        return ResponseEntity.ok(data);
    }
}
