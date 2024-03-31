package com.mitocode.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicDTO {
    @EqualsAndHashCode.Include
    private Integer idMedic;
    private String primaryName;
    private String surName;
    private String cmpMedic;
    private String photo;
}
