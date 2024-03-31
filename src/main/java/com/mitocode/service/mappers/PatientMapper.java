package com.mitocode.service.mappers;

import com.mitocode.model.Patient;
import com.mitocode.service.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class PatientMapper {
    @Qualifier("modelMapper")
    private final ModelMapper modelMapper;
    public PatientDto toDto (Patient entity){
        return this.modelMapper.map(entity, PatientDto.class);
    }
    public Patient toEntity (PatientDto entity){
        return this.modelMapper.map(entity, Patient.class);
    }
}
