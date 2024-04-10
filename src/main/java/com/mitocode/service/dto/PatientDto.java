package com.mitocode.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDto {
    private Integer id;
    @NotNull
    @NotEmpty(message = "{firstname.size}")
    @NotBlank
    @Size(min = 3, max = 70)
    private String firstName;
    @NotEmpty(message = "{lastname.size}")
    private String lastName;
    @NotEmpty
    private String dni;
    @NotEmpty
    private String address;
    @NotEmpty
    @NotNull
    @Pattern(regexp = "[0,9]+")
    private String phone;
    @NotEmpty
    @Email
    private String email;
}
