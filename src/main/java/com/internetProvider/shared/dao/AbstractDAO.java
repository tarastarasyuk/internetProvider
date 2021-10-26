package com.internetProvider.shared.dao;

import java.util.List;

public interface AbstractDAO {
    List getAll();
    boolean create(Object elem);
    boolean read(Integer id);
    boolean update(Object elem);
    boolean delete(Object elem);
}
