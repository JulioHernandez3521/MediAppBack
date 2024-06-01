package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IRolService;
import com.mitocode.service.dto.RolDTO;
import com.mitocode.service.mappers.RolMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
public class RolController {
    private final IRolService service;
    private final RolMapper mapper;

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<RolDTO>> findAllPageable(Pageable pageable){
        return  ResponseEntity.ok(this.mapper.toDtoPage(this.service.listPage(pageable)));
    }

//    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.mapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RolDTO> save(@Valid @RequestBody RolDTO patient){
        RolDTO obj =this.mapper.toDto(this.service.save(this.mapper.toEntity(patient)));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable("id")Integer id, @RequestBody RolDTO patient) {
        patient.setIdRol(id);
        return ResponseEntity.ok(this.mapper.toDto(this.service.update(id,this.mapper.toEntity(patient))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<RolDTO> findByHateoas(@PathVariable("id") Integer id){
        EntityModel<RolDTO> resource = EntityModel.of(this.mapper.toDto(this.service.findById(id)));
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("Sings-info-byId"));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resource.add(link2.withRel("Sings-info-all"));
        return resource;
    }

}
