package com.mitocode.service.mappers;

import com.mitocode.model.Rol;
import com.mitocode.service.dto.RolDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class RolMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public RolDTO toDto (Rol entity){
        return this.mapper.map(entity, RolDTO.class);
    }
    public Rol toEntity (RolDTO entity){
        return this.mapper.map(entity, Rol.class);
    }
    public List<Rol> toEntityList (List<RolDTO> entity){
        return entity.stream().map(b->mapper.map(b, Rol.class)).collect(Collectors.toList());
    }
    public List<RolDTO> toDtoList (List<Rol> entity){
        return entity.stream().map(e -> this.mapper.map(e, RolDTO.class)).collect(Collectors.toList());
    }
    public Page<RolDTO> toDtoPage (Page<Rol> entity){
        return entity.map(e -> this.mapper.map(e, RolDTO.class));
    }
}
