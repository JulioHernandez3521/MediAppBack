package com.mitocode.service.dto.Seed;

import com.mitocode.service.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientListDto {
    private List<PatientDto> patientDtos;
}
