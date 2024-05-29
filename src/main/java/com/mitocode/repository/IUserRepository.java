package com.mitocode.repository;

import com.mitocode.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IGenericRepo<User, Integer> {
    User findFirstByUsername(String email);
}
