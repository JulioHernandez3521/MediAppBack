package com.mitocode.service;

import com.mitocode.model.Signs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISignsService extends ICRUD<Signs, Integer> {
    Page<Signs> listPage(Pageable pageable);
}
