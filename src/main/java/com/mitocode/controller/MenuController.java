package com.mitocode.controller;

import com.mitocode.service.IMenuService;
import com.mitocode.service.dto.MenuDTO;
import com.mitocode.service.mappers.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
