package com.mitocode.service;

import com.mitocode.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolService extends ICRUD<Rol, Integer> {
    Page<Rol> listPage(Pageable pageable);
}
