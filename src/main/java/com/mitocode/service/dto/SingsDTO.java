package com.mitocode.service.dto;

import com.mitocode.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingsDTO {
    private Integer id;
    private LocalDateTime date;
    private String temperatura;
    private String  pulso;
    private String ritmoRespiratorio;
    private Patient patient;
}
