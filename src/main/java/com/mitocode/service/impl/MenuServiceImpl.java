package com.mitocode.service.impl;

import com.mitocode.model.Menu;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IMenuRepo;
import com.mitocode.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu,Integer> implements IMenuService {

    private final IMenuRepo repo;

    @Override
    public List<Menu> getMenuByUserName(String userName) {
        String contextUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.repo.getMenuByUserName(contextUserName);
    }

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }
    @Override
    public Page<Menu> listPage(Pageable pageable) {
        return this.repo.findAll(pageable);
    }
}
