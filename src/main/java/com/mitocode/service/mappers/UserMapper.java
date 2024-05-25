package com.mitocode.service.mappers;

import com.mitocode.model.User;
import com.mitocode.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class UserMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public UserDTO toDto (User entity){
        return this.mapper.map(entity, UserDTO.class);
    }
    public User toEntity (UserDTO entity){
        return this.mapper.map(entity, User.class);
    }
    public List<User> toEntityList (List<UserDTO> entity){
        return entity.stream().map(b->mapper.map(b, User.class)).collect(Collectors.toList());
    }
    public List<UserDTO> toDtoList (List<User> entity){
        return entity.stream().map(e -> this.mapper.map(e, UserDTO.class)).collect(Collectors.toList());
    }
    public Page<UserDTO> toDtoPage (Page<User> entity){
        return entity.map(e -> this.mapper.map(e, UserDTO.class));
    }
}
