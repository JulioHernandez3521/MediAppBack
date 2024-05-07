package com.mitocode.repository;

import com.mitocode.model.ConsultExam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer> {

//    @Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES(:idConsult, :idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);

//    List<Exam> findAllByConsult_IdConsult(Integer examId);
    @Query(value = "SELECT new com.mitocode.model.ConsultExam(ce.exam) FROM ConsultExam ce WHERE ce.consult.idConsult = :idConsult")
    List<ConsultExam> findByConsult(@Param("idConsult") Integer idConsult);
}
