package com.mitocode.service.mappers;

import com.mitocode.model.Specialty;
import com.mitocode.service.dto.SpecialtyDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class SpecialtyMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public SpecialtyDTO toDto (Specialty entity){
        return this.mapper.map(entity, SpecialtyDTO.class);
    }
    public Specialty toEntity (SpecialtyDTO entity){
        return this.mapper.map(entity, Specialty.class);
    }
    public List<Specialty> toEntityList (List<SpecialtyDTO> entity){
        return entity.stream().map(b->mapper.map(b,Specialty.class)).collect(Collectors.toList());
    }
    public List<SpecialtyDTO> toDtoList (List<Specialty> entity){
        return entity.stream().map(e -> this.mapper.map(e, SpecialtyDTO.class)).collect(Collectors.toList());
    }
}
