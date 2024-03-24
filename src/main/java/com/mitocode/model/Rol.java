package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rol {
    @Id
    @EqualsAndHashCode.Include
    private Integer idRol;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 100)
    private String description;
}
