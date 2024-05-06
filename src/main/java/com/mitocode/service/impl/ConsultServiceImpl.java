package com.mitocode.service.impl;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.repository.IConsultExamRepo;
import com.mitocode.repository.IConsultRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    //@Autowired
    private final IConsultRepo consultRepo;// = new ConsultRepo();
    private final IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultRepo.save(consult); //GUARDAR MAESTRO DETALLE
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam())); //INSERTANDO EN TABLA CONSULT_EXAM

        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullName) {
        return this.consultRepo.search(dni,fullName);
    }

    @Override
    public List<Consult> searchbyDates(LocalDateTime d1, LocalDateTime d2) {
        final int OFFSET_DAYS= 1;
        return this.consultRepo.searchByDates(d1,d2.plusDays(OFFSET_DAYS));
    }
}
