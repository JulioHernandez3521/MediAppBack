package com.mitocode.service.mappers;

import com.mitocode.model.Exam;
import com.mitocode.model.Menu;
import com.mitocode.service.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class MenuMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;

    public MenuDTO toDto (Menu entity){
        return this.mapper.map(entity, MenuDTO.class);
    }

    public Menu toEntity (MenuDTO entity){
        return this.mapper.map(entity, Menu.class);
    }

    public List<Menu> toEntityList (List<MenuDTO> entity){
        return this.mapper.map(entity, new TypeToken<List<Exam>>(){}.getType());
    }
    public List<MenuDTO> toDtoList (List<Menu> entity){
        return entity.stream().map(e -> this.mapper.map(e, MenuDTO.class)).collect(Collectors.toList());
    }
}
