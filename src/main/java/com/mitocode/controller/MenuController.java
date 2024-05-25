package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IMenuService;
import com.mitocode.service.dto.MenuDTO;
import com.mitocode.service.mappers.MenuMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;
    private final MenuMapper mapper;

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenuByUser(@RequestBody String username) {
        return ResponseEntity.ok(this.mapper.toDtoList(this.service.getMenuByUserName(username)));
    }
    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll(){
        return ResponseEntity.ok(this.service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<MenuDTO>> findAllPageable(Pageable pageable){
        return  ResponseEntity.ok(this.mapper.toDtoPage(this.service.listPage(pageable)));
    }

    //    @Timed()
    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.mapper.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<MenuDTO> save(@Valid @RequestBody MenuDTO menu){
        MenuDTO obj =this.mapper.toDto(this.service.save(this.mapper.toEntity(menu)));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenu()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable("id")Integer id, @RequestBody MenuDTO menu) {
        menu.setIdMenu(id);
        return ResponseEntity.ok(this.mapper.toDto(this.service.update(id,this.mapper.toEntity(menu))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id")Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<MenuDTO> findByHateoas(@PathVariable("id") Integer id){
        EntityModel<MenuDTO> resource = EntityModel.of(this.mapper.toDto(this.service.findById(id)));
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("Sings-info-byId"));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
        resource.add(link2.withRel("Sings-info-all"));
        return resource;
    }

}
