package com.retail.repository;

import java.util.List;

public interface Repository<E, ID> {
    public E save(E entity);
    public E findById(ID id);
    public List<E> findAll();
    public E update(E entity);
    public E delete(ID id);
}