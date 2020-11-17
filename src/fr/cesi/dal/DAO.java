package fr.cesi.dal;

import java.util.List;

public interface DAO<T> {
    List<T> get();
    void update();
    void delete();
    void insert();
}
