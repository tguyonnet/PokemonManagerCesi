package fr.cesi.dal;

import java.util.List;

public interface DAO<T> {
    T get(int i);

    List<T> getAll();

    void update(T t);

    void delete(int i);

    T insert(T t);
}
