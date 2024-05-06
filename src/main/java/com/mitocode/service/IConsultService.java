package com.mitocode.service;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exam);
    List<Consult> search(String dni, String fullName);
    List<Consult> searchbyDates(LocalDateTime d1, LocalDateTime d2);
}
