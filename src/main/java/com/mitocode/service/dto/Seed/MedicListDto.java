package com.mitocode.service.dto.Seed;

import com.mitocode.service.dto.MedicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicListDto {
    private List<MedicDTO> medicDTOS;
}
