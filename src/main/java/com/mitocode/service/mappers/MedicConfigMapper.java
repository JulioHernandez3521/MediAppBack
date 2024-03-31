package com.mitocode.service.mappers;

import com.mitocode.model.Medic;
import com.mitocode.service.dto.MedicDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.internal.TypeResolvingList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class MedicConfigMapper {

    @Qualifier("medicMapper")
    private final ModelMapper mapper;
    public MedicDTO toDto (Medic entity){
        return this.mapper.map(entity, MedicDTO.class);
    }
    public List<MedicDTO> toDtoList (List<Medic> entity){
        return entity.stream().map(e -> this.mapper.map(e, MedicDTO.class)).collect(Collectors.toList());
    }
    public Medic toEntity (MedicDTO entity){
        return this.mapper.map(entity, Medic.class);
    }
    public List<Medic> toEntityList (List<MedicDTO> entity){
        return entity.stream().map(b->mapper.map(b,Medic.class)).collect(Collectors.toList());
    }
}
