package com.mitocode.service.impl;

import com.mitocode.model.ConsultExam;
import com.mitocode.repository.IConsultExamRepo;
import com.mitocode.service.IConsultExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ConsultExmaServiceImpl implements IConsultExamService {

    private final IConsultExamRepo repo;


    @Override
    public List<ConsultExam> getConsultExams(Integer consultId) {
        return this.repo.findByConsult(consultId);
    }
}
