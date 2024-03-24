package com.mitocode.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDto {
    private Integer id;
    @NotEmpty(message = "The FistName field is required")
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String dni;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;
}
