package com.dediev.crudApp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    List<T> getAll();

    T save(T t);

    void deleteById(ID id);
}
