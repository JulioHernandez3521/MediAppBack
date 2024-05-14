package com.mitocode.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.model.IConsultProjectionDTO;
import com.mitocode.repository.IConsultExamRepo;
import com.mitocode.repository.IConsultRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IConsultService;
import com.mitocode.service.dto.ConsultProcDTO;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    //@Autowired
    private final IConsultRepo consultRepo;// = new ConsultRepo();
    private final IConsultExamRepo ceRepo;
    private final ObjectMapper mapper;
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

    @Override
    public List<IConsultProjectionDTO> consultProjection() {
        return this.consultRepo.callProcedureOrFuntion();
    }

    @Override
    public List<ConsultProcDTO> consultProjectionNative() {
        return this.consultRepo.callProcedureOrFuntionNative().stream().map(e -> {
            ConsultProcDTO dto = new ConsultProcDTO();
            dto.setConsultdate((String) e[1]);
            dto.setQuantity((Integer) e[0]);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public byte[] generateReport() throws Exception {
//         byte[] data = null;
         Map<String, Object> parameters = new HashMap<>();
         parameters.put("txt_title","MEDIAPP REPORT");

        File file = new ClassPathResource("/reports/consultas.jasper").getFile();
        return JasperExportManager.exportReportToPdf(
                JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(this.consultProjectionNative()))
        );
    }
}
