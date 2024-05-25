package com.mitocode.service.impl;

import com.mitocode.model.Rol;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IRolRepository;
import com.mitocode.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {

    private final IRolRepository repository;

    @Override
    protected IGenericRepo<Rol, Integer> getRepo() {
        return this.repository;
    }

    @Override
    public Page<Rol> listPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
