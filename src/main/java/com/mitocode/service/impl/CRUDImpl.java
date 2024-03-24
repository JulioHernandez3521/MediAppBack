package com.mitocode.service.impl;

import com.mitocode.exception.ModelNotFounException;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {
    protected abstract IGenericRepo<T,ID> getRepo();
    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFounException("ID NOT FOUN"+id));
    }

    @Override
    public T save(T patient) {
        return this.getRepo().save(patient);
    }

    @Override
    public T update(ID id, T patient) {
        this.findById(id);
        return this.save(patient);
    }

    @Override
    public void delete(ID id) {
        T entity = this.findById(id);
        this.getRepo().delete(entity);
    }
}
