package com.mitocode.controller;

import com.mitocode.service.dto.ConsultExamDTO;
import com.mitocode.service.IConsultExamService;
import com.mitocode.service.mappers.ConsultExamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consultExams")
@RequiredArgsConstructor
public class ConsultExamController {
    private final IConsultExamService service;
    private final ConsultExamMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<ConsultExamDTO>> getConsultExamById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.mapper.toDtoList(this.service.getConsultExams(id)));
    }


}
