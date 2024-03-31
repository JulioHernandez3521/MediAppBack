package com.mitocode.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @NotEmpty(message = "The primaryName field is required")
    @NotBlank
    @Size(min = 3, max = 70)
    private String primaryName;
    @NotNull
    @NotEmpty(message = "The surName field is required")
    @NotBlank
    @Size(min = 3, max = 70)
    private String surName;
    @NotNull
    @NotEmpty(message = "The cmpMedic field is required")
    @NotBlank
    @Size(min = 3, max = 12)
    private String cmpMedic;
    private String photo;
}
