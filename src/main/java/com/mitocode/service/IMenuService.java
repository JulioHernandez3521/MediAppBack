package com.mitocode.service;

import com.mitocode.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMenuService extends ICRUD<Menu, Integer> {
    List<Menu> getMenuByUserName(String userName);
    Page<Menu> listPage(Pageable pageable);
}
