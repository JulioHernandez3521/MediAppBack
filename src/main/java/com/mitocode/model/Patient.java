package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false,length = 70)
    private String firstName;
    @Column(nullable = false, length = 70)
    private String lastName;
    @Column(nullable = false, length = 8)
    private String dni;
    @Column(length = 50)
    private String address;
    @Column(length = 20)
    private String phone;
    @Column(length = 40)
    private String email;
}
