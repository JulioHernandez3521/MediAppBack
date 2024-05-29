package com.mitocode.service.impl;

import com.mitocode.model.User;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IUserRepository;
import com.mitocode.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return this.repository;
    }

    @Override
    public Page<User> listPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public User save(User data){
        String hashedPassword = passwordEncoder.encode(data.getPassword());
        data.setPassword(hashedPassword);
        return super.save(data);
    }

    @Override
    public User update(Integer id, User data){
        User old = super.findById(id);
        if(old == null) return super.save(data);
        if(old.getPassword().equals(data.getPassword()))return super.save(data);
        String hashedPassword = passwordEncoder.encode(data.getPassword());
        data.setPassword(hashedPassword);
        return super.save(data);
    }
}
