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
public class ConsultDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name = "id_consult",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_CONSULT"))
    private Consult consult;

    @Column(nullable = false, length = 50)
    private String diagnosis;

    @Column(nullable = false, length = 300)
    private String treatment;


}
