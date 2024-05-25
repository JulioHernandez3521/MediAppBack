package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IUserService;
import com.mitocode.service.dto.UserDTO;
import com.mitocode.service.mappers.UserMapper;
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
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final IUserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<UserDTO>> findAllPageable(Pageable pageable){
        return  ResponseEntity.ok(this.mapper.toDtoPage(this.service.listPage(pageable)));
    }

//    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.mapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO patient){
        UserDTO obj =this.mapper.toDto(this.service.save(this.mapper.toEntity(patient)));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUser()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id")Integer id, @RequestBody UserDTO patient) {
        patient.setIdUser(id);
        return ResponseEntity.ok(this.mapper.toDto(this.service.update(id,this.mapper.toEntity(patient))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<UserDTO> findByHateoas(@PathVariable("id") Integer id){
        EntityModel<UserDTO> resource = EntityModel.of(this.mapper.toDto(this.service.findById(id)));
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("Sings-info-byId"));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resource.add(link2.withRel("Sings-info-all"));
        return resource;
    }

}
