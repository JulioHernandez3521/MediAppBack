package com.mitocode.service.mappers;

import com.mitocode.model.Signs;
import com.mitocode.service.dto.SingsDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class SignsMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public SingsDTO toDto (Signs entity){
        return this.mapper.map(entity, SingsDTO.class);
    }
    public Signs toEntity (SingsDTO entity){
        return this.mapper.map(entity, Signs.class);
    }
    public List<Signs> toEntityList (List<SingsDTO> entity){
        return entity.stream().map(b->mapper.map(b, Signs.class)).collect(Collectors.toList());
    }
    public List<SingsDTO> toDtoList (List<Signs> entity){
        return entity.stream().map(e -> this.mapper.map(e, SingsDTO.class)).collect(Collectors.toList());
    }
    public Page<SingsDTO> toDtoPage (Page<Signs> entity){
        return entity.map(e -> this.mapper.map(e, SingsDTO.class));
    }
}
