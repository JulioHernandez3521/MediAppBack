package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(ConsultExamPK.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultExam {
    @Id
    private  Consult consult;
    @Id
    private  Exam exam;

}
