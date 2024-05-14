package com.mitocode.service.mappers;

import com.mitocode.model.Patient;
import com.mitocode.service.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class PatientMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public PatientDto toDto (Patient entity){
        return this.mapper.map(entity, PatientDto.class);
    }
    public Patient toEntity (PatientDto entity){
        return this.mapper.map(entity, Patient.class);
    }
    public List<Patient> toEntityList (List<PatientDto> entity){
        return entity.stream().map(b->mapper.map(b,Patient.class)).collect(Collectors.toList());
    }
    public List<PatientDto> toDtoList (List<Patient> entity){
        return entity.stream().map(e -> this.mapper.map(e, PatientDto.class)).collect(Collectors.toList());
    }
    public Page<PatientDto> toDtoPage (Page<Patient> entity){
        return entity.map(e -> this.mapper.map(e, PatientDto.class));
    }
}
