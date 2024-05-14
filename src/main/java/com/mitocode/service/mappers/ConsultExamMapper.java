package com.mitocode.service.mappers;

import com.mitocode.model.ConsultExam;
import com.mitocode.model.Exam;
import com.mitocode.service.dto.ConsultExamDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class ConsultExamMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;

    public ConsultExamDTO toDto (ConsultExam entity){
        return this.mapper.map(entity, ConsultExamDTO.class);
    }

    public ConsultExam toEntity (ConsultExamDTO entity){
        return this.mapper.map(entity, ConsultExam.class);
    }

    public List<ConsultExam> toEntityList (List<ConsultExamDTO> entity){
        return this.mapper.map(entity, new TypeToken<List<Exam>>(){}.getType());
    }
    public List<ConsultExamDTO> toDtoList (List<ConsultExam> entity){
        return entity.stream().map(e -> this.mapper.map(e, ConsultExamDTO.class)).collect(Collectors.toList());
    }
}
