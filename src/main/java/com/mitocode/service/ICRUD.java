package com.mitocode.service;

import java.util.List;

public interface ICRUD<T,ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T patient);
    T update(ID id, T patient);
    void delete(ID id);
}
