package com.mitocode.service.impl;

import com.mitocode.model.User;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IUserRepository;
import com.mitocode.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepository repository;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return this.repository;
    }

    @Override
    public Page<User> listPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
