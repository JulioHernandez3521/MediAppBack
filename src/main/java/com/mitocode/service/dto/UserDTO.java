package com.mitocode.service.dto;

import com.mitocode.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    private Integer idUser;
    private String username;
    private String password;
    private boolean enabled;
    private List<Rol> roles;
}
