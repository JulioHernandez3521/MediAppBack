package com.mitocode.repository;

import com.mitocode.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IGenericRepo<User, Integer> {
    // Query derivado
    User findOneByUsername(String name);
}
