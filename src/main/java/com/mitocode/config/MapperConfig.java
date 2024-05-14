package com.mitocode.config;

import com.mitocode.model.Consult;
import com.mitocode.model.Medic;
import com.mitocode.service.dto.ConsultDTO;
import com.mitocode.service.dto.MedicDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean("modelMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean("medicMapper")
    public ModelMapper medicMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<MedicDTO, Medic> type1 = mapper.createTypeMap(MedicDTO.class, Medic.class);
        type1.addMapping(MedicDTO::getIdMedic, (dest, v) -> dest.setIdMedic((Integer) v));
        type1.addMapping(MedicDTO::getPhoto, (dest, v) -> dest.setPhotoUrl((String) v));
        type1.addMapping(MedicDTO::getPrimaryName, (dest, v) -> dest.setFistName((String) v));
        type1.addMapping(MedicDTO::getSurName, (dest, v) -> dest.setLastName((String) v));

        TypeMap<Medic, MedicDTO> type2 = mapper.createTypeMap(Medic.class, MedicDTO.class);
        type2.addMapping(Medic::getIdMedic, (dest, v) -> dest.setIdMedic((Integer) v));
        type2.addMapping(Medic::getPhotoUrl, (dest, v) -> dest.setPhoto((String) v));
        type2.addMapping(Medic::getFistName, (dest, v) -> dest.setPrimaryName((String) v));
        type2.addMapping(Medic::getLastName, (dest, v) -> dest.setSurName((String) v));


        return mapper;
    }
    @Bean("ConsultMapper")
    public ModelMapper consultMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Consult.class, ConsultDTO.class)
                .addMapping(e-> e.getMedic().getFistName(), (dest, v) -> dest.getMedic().setPrimaryName((String) v))
                .addMapping(e -> e.getMedic().getLastName(), (dest, v) -> dest.getMedic().setSurName((String) v));
        return mapper;
    }
}
