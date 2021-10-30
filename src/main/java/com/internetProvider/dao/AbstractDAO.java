package com.internetProvider.dao;

import java.util.List;

public interface AbstractDAO<E> {
    List<E> getAll();
    boolean create(E entity);
    E read(int entityId);
    boolean update(E entity, E newEntity);
    boolean delete(E entity);
}
