package com.mitocode.repository;

import com.mitocode.model.Consult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultRepo extends IGenericRepo<Consult, Integer> {

    //JPQL Java Persistence Query Language
    @Query("FROM Consult c WHERE lower( c.patient.dni) like %:dni% OR LOWER(c.patient.firstName) like %:fullName% OR LOWER(c.patient.lastName) like %:fullName%")
    List<Consult> search(@Param("dni") String dni, @Param("fullName") String fullName);

    //BETWEEN WORKS LIKE THIS >= | <
    @Query("FROM Consult c WHERE c.consultDate BETWEEN :d1 AND :d2")
    List<Consult> searchByDates(@Param("d1")LocalDateTime d1, @Param("d2")LocalDateTime d2);

}
