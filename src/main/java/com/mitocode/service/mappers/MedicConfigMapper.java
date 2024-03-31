package com.mitocode.service.mappers;

import com.mitocode.model.Medic;
import com.mitocode.service.dto.MedicDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class MedicConfigMapper {

    @Qualifier("medicMapper")
    private final ModelMapper mapper;
    public MedicDTO toDto (Medic entity){
        return this.mapper.map(entity, MedicDTO.class);
    }
    public Medic toEntity (MedicDTO entity){
        return this.mapper.map(entity, Medic.class);
    }
}
