package com.mitocode.service.dto;

import com.mitocode.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Integer idMenu;
    private String icon;
    private String name;
    private String url;
    private List<Rol> roles;
}
