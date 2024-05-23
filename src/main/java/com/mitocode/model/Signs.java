package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Signs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false,length = 70)
    private String temperatura;
    @Column(nullable = false,length = 70)
    private String  pulso;
    @Column(nullable = false,length = 12)
    private String ritmoRespiratorio;
    @ManyToOne
    @JoinColumn(name = "id_patient",
            nullable = false, foreignKey = @ForeignKey(name = "FK_SINGS_PATIENT"))
    private Patient patient;
}
