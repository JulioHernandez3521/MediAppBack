package com.mitocode.service.impl;

import com.mitocode.model.Medic;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.MedicRepository;
import com.mitocode.service.MedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic,Integer> implements MedicService {
    private final MedicRepository repository;
    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return this.repository;
    }
}
