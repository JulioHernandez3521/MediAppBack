package com.mitocode.service;

import com.mitocode.model.ConsultExam;

import java.util.List;

public interface IConsultExamService {
//    List<Exam> findAllExamsByConsultId(Integer consultId);
    List<ConsultExam> getConsultExams(Integer id);
}
