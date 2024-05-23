package com.mitocode.service.impl;

import com.mitocode.model.Signs;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.ISignsRepository;
import com.mitocode.service.ISignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignsServiceImpl extends CRUDImpl<Signs, Integer> implements ISignsService {

    private final ISignsRepository repository;

    @Override
    protected IGenericRepo<Signs, Integer> getRepo() {
        return this.repository;
    }

    @Override
    public Page<Signs> listPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
