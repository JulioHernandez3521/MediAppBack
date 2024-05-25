package com.mitocode.service;

import com.mitocode.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends ICRUD<User, Integer> {
    Page<User> listPage(Pageable pageable);
}
