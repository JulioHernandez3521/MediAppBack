package com.mitocode.service.mappers;

import com.mitocode.model.Exam;
import com.mitocode.service.dto.ExamDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // This annotation should us obligate to give the final attributes
public class ExamMapper {
    @Qualifier("modelMapper")
    private final ModelMapper mapper;
    public ExamDTO toDto (Exam entity){
        return this.mapper.map(entity, ExamDTO.class);
    }
    public Exam toEntity (ExamDTO entity){
        return this.mapper.map(entity, Exam.class);
    }
    public List<Exam> toEntityList (List<ExamDTO> entity){
        return entity.stream().map(b->mapper.map(b,Exam.class)).collect(Collectors.toList());
    }
    public List<ExamDTO> toDtoList (List<Exam> entity){
        return entity.stream().map(e -> this.mapper.map(e, ExamDTO.class)).collect(Collectors.toList());
    }
}
