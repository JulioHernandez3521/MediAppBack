package com.mitocode.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.service.MedicService;
import com.mitocode.service.dto.MedicDTO;
import com.mitocode.service.dto.Seed.MedicListDto;
import com.mitocode.service.mappers.MedicConfigMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("${medic.controller.path}")
@RequiredArgsConstructor
public class MedicController {
    private final Logger log = LoggerFactory.getLogger(MedicController.class);
    private final MedicService service;
    private final MedicConfigMapper mapper;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(mapper::toDto).toList());
    }
//    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.mapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<MedicDTO> save(@Valid @RequestBody MedicDTO patient){
        MedicDTO obj =this.mapper.toDto(this.service.save(this.mapper.toEntity(patient)));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PostMapping("/all")
    public ResponseEntity<List<MedicDTO>> saveAll(@RequestBody MedicListDto patient){
        List<MedicDTO> obj =this.mapper.toDtoList(this.service.saveAll(this.mapper.toEntityList(patient.getMedicDTOS())));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri();
        log.info("Save Itemes, Total: {}",patient.getMedicDTOS().size());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id")Integer id, @RequestBody MedicDTO patient) {
        patient.setIdMedic(id);
        return ResponseEntity.ok(this.mapper.toDto(this.service.update(id,this.mapper.toEntity(patient))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicDTO> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicDTO> findByHateoas(@PathVariable("id") Integer id){
        EntityModel<MedicDTO> resource = EntityModel.of(this.mapper.toDto(this.service.findById(id)));
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info-byId"));
        return resource;
    }
    @PostMapping("/seed")
    public ResponseEntity<List<MedicDTO>> saveAll(){
        List<MedicDTO> data = new ArrayList<>();
        TypeReference<List<MedicDTO>> typeReference = new TypeReference<List<MedicDTO>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/seed/medics.json");
        try {
            data = this.objectMapper.readValue(inputStream,typeReference);
            data = this.mapper.toDtoList(this.service.saveAll(this.mapper.toEntityList(data)));
            log.info("Total items saved: {}",data.size());
        } catch (IOException e){
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(data);
    }
}
