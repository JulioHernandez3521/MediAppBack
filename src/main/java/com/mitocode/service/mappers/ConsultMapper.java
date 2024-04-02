package com.mitocode.service.mappers;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.dto.ConsultDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class ConsultMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public ConsultDTO toDto (Consult entity){
        return this.mapper.map(entity, ConsultDTO.class);
    }
    public Consult toEntity (ConsultDTO entity){
        return this.mapper.map(entity, Consult.class);
    }
    public List<Consult> toEntityList (List<ConsultDTO> entity){
        return this.mapper.map(entity, new TypeToken<List<Exam>>(){}.getType());
    }
    public List<ConsultDTO> toDtoList (List<Consult> entity){
        return entity.stream().map(e -> this.mapper.map(e, ConsultDTO.class)).collect(Collectors.toList());
    }
}
